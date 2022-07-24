## aop_medium

### 1. ProceedingJoinPoint

`ProceedingJoinPoint` 注解环绕通知的入参，它是基于 `JoinPoint` 的扩展，扩展了`proceed方法`，**相当于invoke**。
能通过调用这个方法来执行被代理对象的原始方法，当然也可以修改方法的入参。

使用`@Order`或实现**Orderd接口**来控制切面的执行顺序

`AopContext`**能在当前代理对象中取到自身，而不用为了拦截自身的方法而再将自身注入进来**，