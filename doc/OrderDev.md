# 订单模块开发

## 限流算法

限流措施是系统高可用的一种手段

- 使用并发与连接控制进行限流；
- 使用漏桶算法和令牌桶算法进行限流；

### 漏桶算法

![漏桶算法](assets/ratelimit.png)

因为处理的速度是固定的，请求进来的速度是未知的，可能突然进来很多请求，没来得及处理的请求就先放在桶里，既然是个桶，肯定是有容量上限，如果桶满了，那么新进来的请求就丢弃。

弊端：无法应对短时间的突发流量；

### 令牌桶算法

![tokenbucket](assets/tokenbucket.jpg)

令牌桶分为2个动作，动作1（固定速率往桶中存入令牌）、动作2（客户端如果想访问请求，先从桶中获取token）。

是对漏桶算法的一种改进，桶算法能够限制请求调用的速率，而令牌桶算法能够在限制调用的平均速率的同时，还允许一定程度的突发调用。

```java
public class TokenBucket {

    /**
     * 桶的容量
     */
    private int bucketNums = 100;

    /**
     * 流入速度
     */
    private int rate = 1;

    /**
     * 当前令牌数
     */
    private int nowTokens;

    /**
     * 上次刷新当前令牌数的时间
     */
    private long lastTime = getNowTime();

    private long getNowTime() {
        return System.currentTimeMillis();
    }

    public boolean getToken() {

        // 记录当前时间
        long nowTime = getNowTime();

        // 刷新令牌
        nowTokens += (nowTime - lastTime) * rate;

        // 若令牌数比桶容量大，则改为桶容量
        nowTokens = Math.min(bucketNums, nowTokens);
        System.out.println("当前令牌数：" + nowTokens);

        // 修改上次刷新令牌数的时间为当前时间
        lastTime = nowTime;

        // 判断令牌是否足够
        if (nowTokens > 0) {
            nowTokens--;
            return true;
        } else {
            return false;
        }
    }
}
```

优化：使用guava包，通过RateLimiter类的create方法，创建限流器。

## Hystrix熔断降级

![Hytrix熔断降级](assets/hystrix.png)

### 服务熔断作用

当某服务出现不可用或响应超时的情况时，为了防止整个系统出现雪崩，暂时停止对该服务的调用。

### 服务降级作用

从整个系统的负荷情况考虑，为了预防某些功能（业务场景）出现负荷过载或者响应慢的情况，在其内部暂时舍弃对一些非核心的接口和数据的请求，直接返回一个提前准备好的fallback（退路）错误处理信息。这样，虽然提供的是一个有损的服务，但却保证了整个系统的稳定性和可用性。

### 服务降级和熔断的对比

相同点：

+ 目标一致：都是从可用性和可靠性出发，为了防止系统崩溃；

+ 用户体验类似：最终都让用户体验到的是某些功能暂时不可用；

不同点：

+ 触发原因不同：服务熔断一般是某个服务（下游服务）故障引起，而服务降级一般是从整体负荷考虑； 

### 开发

在`guns-gateway`的pom.xml中添加依赖：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>2.0.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    <version>2.0.0.RELEASE</version>
</dependency>
```

在启动类上添加注解：

```java
...
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableHystrix
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
```

给需要熔断器控制的方法添加注解，并定义`fallbackMethod`方法，用于熔断降级：

```java
/**
  * 服务降级
  */
public ResponseVO<?> error(Integer fieldId, String soldSeats, String seatsName) {
    return ResponseVO.serviceFail("抱歉，下单的人太多了，请稍后重试");
}

@HystrixCommand(fallbackMethod = "error",
                commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
                },
                threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
                })
@RequestMapping(value = "buyTickets", method = RequestMethod.POST)
public ResponseVO<?> buyTickets(Integer fieldId, String soldSeats, String seatsName) {
    ...
}
```

修改`CurrentUser`类，将ThreadLocal改为InheritableThreadLocal：

```java
public class CurrentUser {
    /**
     * 线程绑定的存储空间，因为Hystrix熔断器会进行线程切换，
     * 切换后则拿不到登录时的ThreadLocal，也就拿不到用户信息，误判为用户未登录。
     */
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
	...
}
```

