# 项目中用到的Dubbo特性

## 负载均衡

## 异步调用

![调用流程](assets/future.jpg)

官方文档：https://dubbo.apache.org/zh-cn/docs/user/demos/async-call.html

由于没有提供方法级别的注解可以配置`async`参数，如果加在服务级别，即客户端的`@Reference`或者服务端的`@Service`上，又会影响不需要使用异步调用的地方。

所以，将需要异步调用的方法抽取出来，重新创建一个专门用于异步调用的Api及其实现类：

```java
public interface FilmAsyncServiceApi {

    FilmDescVO getFilmDesc(String filmId);

    ActorVO getDirectorInfo(String filmId);

    List<ActorVO> getActors(String filmId);
    
}

@Component
@Service(interfaceClass = FilmAsyncServiceApi.class)
public class DefaultFilmAsyncServiceImpl implements FilmAsyncServiceApi {
	...
}
```

在`Controller`中声明异步调用的接口，并使用`RpcContext`实现异步调用：

```java
@RestController
@RequestMapping("/film/")
public class FilmController {

    @Reference(interfaceClass = FilmAsyncServiceApi.class, async = true)
    private FilmAsyncServiceApi filmAsyncServiceApi;

	@RequestMapping(value = "films/{searchParam}", method = RequestMethod.GET)
    public ResponseVO<?> films(@PathVariable("searchParam") String searchParam, int searchType) throws InterruptedException, ExecutionException {
			...
            // 获取影片描述信息
            filmAsyncServiceApi.getFilmDesc(filmId);
            Future<FilmDescVO> filmDescVOFuture = RpcContext.getContext().getFuture();

            // 获取导演信息
            filmAsyncServiceApi.getDirectorInfo(filmId);
            Future<ActorVO> directorVOFuture = RpcContext.getContext().getFuture();

            // 获取演员信息
            filmAsyncServiceApi.getActors(filmId);
            Future<List<ActorVO>> actorsFuture = RpcContext.getContext().getFuture();
			...
    }
}
```

在启动类上添加注解`@EnableAsync`：

```java
@SpringBootApplication
@EnableDubboConfiguration
@EnableAsync
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GunsRestApplication.class, args);
    }
}
```

说明：因为此项目基于Dubbo2.6.0版本，所以异步编程接口基于Future，自Dubbo 2.7.0之后的版本，已改为基于CompletableFuture。

> Future和CompletableFuture的比较：
>
> 例如，在开发中需要实现：将两个异步计算合并为一个（这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果）
>
> - 通过轮询判断是否完成，确认完成后，调用get()；
> - 调用get()设置超时时间；
>
> 但是get()会阻塞住调用线程，与异步编程的初衷相背。
>
> Future对异步结果的获取采取了阻塞与轮询的方式；
>
> CompletableFuture源自JDK8，对异步结果的获取采用了回调的方式；

## 结果缓存

官方文档：https://dubbo.apache.org/zh-cn/docs/user/demos/result-cache.html

用于加速热门数据的访问速度，Dubbo提供声明式缓存，以减少用户加缓存的工作量。

### 缓存类型

- `lru` 基于最近最少使用原则删除多余缓存，保持最热的数据被缓存；
- `threadlocal` 当前线程缓存，比如一个页面渲染，用到很多 portal，每个 portal 都要去查用户信息，通过线程缓存，可以减少这种多余访问；
- `jcache` 与 [JSR107](http://jcp.org/en/jsr/detail?id=107') 集成，可以桥接各种缓存实现；

```java
...
public class CinemaController {
    @Reference(interfaceClass = CinemaServiceApi.class, cache = "lru")
    private CinemaServiceApi cinemaServiceApi;
	...
}
```

### 与Redis的区别

+ Dubbo是本地缓存，保存在当前JVM中，多台机器存储多份；
+ Redis则是分布式缓存，多台机器共享缓存

数据量小时选择Dubbo结果缓存，需要计算或共享数据时选择Redis缓存。

## Dubbo连接控制

官方文档：https://dubbo.apache.org/zh-cn/docs/user/demos/config-connections.html

说明：若是长连接，比如Dubbo协议，`connections`表示该服务对每个提供者建立的长连接数。

## Dubbo并发控制

官方文档：https://dubbo.apache.org/zh-cn/docs/user/demos/concurrency-control.html

## Dubbo服务分组

当一个接口有多种实现时，可以用 group 区分。

官方文档：https://dubbo.apache.org/zh-cn/docs/user/demos/service-group.html

```java
@Slf4j
@Component
@Service(interfaceClass = OrderServiceApi.class, group = "orderA", timeout = 9999999)
public class DefaultOrderServiceImplA implements OrderServiceApi{
    ...
}

@Slf4j
@Component
@Service(interfaceClass = OrderServiceApi.class, group = "orderB", timeout = 9999999)
public class DefaultOrderServiceImplB implements OrderServiceApi{
	...
}

public class OrderController {
    @Reference(interfaceClass = OrderServiceApi.class, check = false, group = "orderB")
    private OrderServiceApi orderServiceApiB;

    @Reference(interfaceClass = OrderServiceApi.class, check = false, group = "orderA")
    private OrderServiceApi orderServiceApiA;
	...
}
```

## Dubbo分组聚合

按组合并返回结果 [[1\]](https://dubbo.apache.org/zh-cn/docs/user/demos/group-merger.html#fn1)，比如菜单服务，接口一样，但有多种实现，用group区分，现在消费方需从每种group中调用一次返回结果，合并结果返回，这样就可以实现聚合菜单项。

官方文档：https://dubbo.apache.org/zh-cn/docs/user/demos/group-merger.html

## Dubbo多版本

当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。

可以按照以下的步骤进行版本迁移：

1. 在低压力时间段，先升级一半提供者为新版本
2. 再将所有消费者升级为新版本
3. 然后将剩下的一半提供者升级为新版本

官方文档：https://dubbo.apache.org/zh-cn/docs/user/demos/multi-versions.html

