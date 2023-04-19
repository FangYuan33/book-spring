## 1. SmartLifecycle

- **JavaDoc**:

> 它是 `Lifecycle` 接口的扩展，它能够以特定的顺序在 `ApplicationContext` 刷新（启动）或者停止时执行特定的方法。
> 其中 `isAutoStartup()` 方法控制是否在 `ApplicationContext` 启动时执行 `start()` 方法。
> 当异步停止时，会调用 `stop(Runnable callback)` 方法。任何该接口的实现都必须回调 `callback` (是一个Runnable) 的 `run()` 方法
> 直到完全停止，避免 `ApplicationContext` 的停止被推迟。
> 
> 它扩展了 `Phased`，通过 `getPhase()` 的返回值指示 Bean 应在其中启动和停止的阶段。启动的过程的优先级可以
> 从最低优先级到最高优先级，而停止的过程则是将该优先级反转了过来。任何组件(Components)都能在相同的阶段(Phase)被任意的排序。
> 
> 比如：A 依赖 B，那么 A 应该有一个更低的 phase value，不过在停止的过程中，B 应该在 A 之前停止。
> 
> 容器中的任何 `Lifecycle` 没有实现 `SmartLifecycle` 接口的 Bean 的 phase value 都为0。这样，如果实现 `SmartLifecycle` 接口的 Bean
> 的 phase value 为负，那么它就可以在这些 `Lifecycle` 组件之前启动，如果 phase value 为正，则可以在这些组件之后启动。
> 
> 注意：由于 `SmartLifecycle` 是自动启动的，在任何情况下， `SmartLifecycle` 的实例都将在应用程序上下文启动时初始化。因此，
> 懒加载的Bean实际上不能影响到 `SmartLifecycle` Bean。

### 1.1 