## aop_medium

### 1. ProceedingJoinPoint

`ProceedingJoinPoint` 注解环绕通知的入参，它是基于 `JoinPoint` 的扩展，扩展了`proceed方法`，**相当于invoke**。
能通过调用这个方法来执行被代理对象的原始方法，当然也可以修改方法的入参。

使用`@Order`或实现**Orderd接口**来控制切面的执行顺序

`AopContext`**能在当前代理对象中取到自身，而不用为了拦截自身的方法而再将自身注入进来**

### 2. AOP的失效场景

- **代理对象调用自身的方法时，AOP 通知会失效** 
   - 即在代理对象中直接调用 `this.xxx` 方法
   - 正确做法是借助 `AopContext` 取到当前代理对象并强转，之后调用，这样 AOP 通知依然会执行
    
- 代理对象在**后置处理器还没有初始化的时候**，提前创建了，则 AOP 通知不会织入