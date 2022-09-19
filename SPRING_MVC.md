## Spring MVC

### 1. 异常处理器

- `@ControllerAdvice` 能对Controller进行增强，它只是一个Component，
可以通过basePackages来指定增强的包或者通过assignableTypes来指定要增强的Controller

```java
@ControllerAdvice(basePackages = "com.linkedbear.spring.withdao.controller")
@ControllerAdvice(assignableTypes = UserController.class)
```