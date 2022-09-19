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