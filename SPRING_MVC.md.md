## Spring MVC

### 1. 异常处理器

- `@ControllerAdvice` 能对Controller进行增强，它只是一个Component，
可以通过basePackages来指定增强的包或者通过assignableTypes来指定要增强的Controller

```java
@ControllerAdvice(basePackages = "com.linkedbear.spring.withdao.controller")
@ControllerAdvice(assignableTypes = UserController.class)
```

### 2. 拦截器
- `preHandle` ：在执行 Controller 的方法之前触发，可用于编码、权限校验拦截等
- `postHandle` ：在执行完 Controller 方法后，跳转页面 / 返回 json 数据之前触发
- `afterCompletion` ：在完全执行完 Controller 方法后触发，可用于异常处理、性能监控等

其中`preHandler`方法是**顺序执行**，`postHandler`和`afterCompletion`方法均为**逆序执行**

![img.png](img.png)

### 3. 跨域
跨域是由浏览器的**同源策略**引起的，什么是同源策略？

同源策略规定了三个相同：**协议相同**、**域名 / 主机相同**、**端口相同**。当这三个因素都相同时，则浏览器会认为访问的资源是同一个来源。

所以只要违反其三中的一种，就会引起跨域问题：
1. http 访问 https，或者 https 访问 http
2. 不同域名 / 服务器主机之间的访问
3. 不同端口之间的访问

而同源策略保护的是Cookie，如果你在访问某个网站时，发生了跨域，那么如果将该Cookie信息发送给其他网站，可能会造成信息安全问题

`@CrossOrigin`注解标注在`Controller类`或`方法`上可以解决跨域问题，但是其本质上是在响应头上添加了`Access-Control-Allow-Origin: *`