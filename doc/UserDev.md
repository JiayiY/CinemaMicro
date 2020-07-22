[TOC]



# 用户模块开发

## 构建

在项目中复制一份`guns-gateway`模块并重命名为`guns-user`（修改子模块和主模块的`pom.xml`文件），并在`application.yml`中关闭其鉴权机制（仅网关需要开启），修改端口避免冲突：

```yaml
rest:
  auth-open: false #jwt鉴权机制是否开启(true或者false)
  sign-open: false #签名机制是否开启(true或false)
  
server:
  port: 8083
```

在添加 Dubbo 协议和端口（UserApplication 需要向外部暴露接口，先向注册中心注册，然后通过 Dubbo 协议被远程调用）

```yaml
spring:
  application:
    name: cinema-user
  dubbo:
    server: true
    registry: zookeeper://localhost:2181
    protocol:
      name: dubbo
      port: 20881
```

## 开发

在`guns-user`中对`guns-api`的`UserAPI`编写其实现类，并通过`@Service`向外部暴露：

```java
@Component
@Service(interfaceClass = UserAPI.class)
public class UserServiceImpl implements UserAPI {
...
}
```

在`guns-gateway`的`UserController`中远程调用接口：

```java
@RequestMapping("/user/")
@RestController
public class UserController {
    @Reference(interfaceClass = UserAPI.class)
    private UserAPI userAPI;
	...
}
```

配置`guns-gateway`模块中的`application.yml`，在`jwt`下添加`ignore-url`，用逗号分隔，这些接口将不通过jwt的验证过滤器：

```yaml
jwt:
  header: Authorization   #http请求头所需要的字段
  secret: mySecret        #jwt秘钥
  expiration: 604800      #7天 单位:秒
  auth-path: auth         #认证请求的路径
  md5-key: randomKey      #md5加密混淆key
  ignore-url: /user/register,/user/check # 忽略列表

```

在jwt配置类中添加ignoreUrl，`@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)` 表示用于从`application.yml`中获取`jwt`开头的配置属性：

```java
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
public class JwtProperties {
	...
    private String ignoreUrl = "";
    ...
}
```

在`AuthFilter`的`doFilterInternal()`中添加忽略配置列表的代码：

```java
// 配置忽略列表
String ignoreUrl = jwtProperties.getIgnoreUrl();
String[] ignoreUrls = ignoreUrl.split(",");
if (ignoreUrls.length != 0) {
    for (String url : ignoreUrls) {
        if (request.getServletPath().startsWith(url)) {
            chain.doFilter(request, response);
            return;
        }
    }
}
```

使用Threadlocal保存用户信息，在`guns-gateway`的`common`下创建 `CurrentUser`类保存`userId`（不直接保存`userInfoModel`，因为更省空间，避免用户量大时出现内存溢出）：

```java
public class CurrentUser {
    
    // 仅存储userId 防止存储整个对象占用过多的内存空间 OOM
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void saveUserId(String userId) {
        THREAD_LOCAL.set(userId);
    }

    public static String getCurrentUser() {
        return THREAD_LOCAL.get();
    }
    
}
```

在每个模块的`test`目录下，都有`generator.EntityGenerator`用于根据数据库表生成实体类，根据需要稍微修改一下参数即可。在`guns-user`模块中生成：UserT, UserTMapper, UserTMapper.xml。

