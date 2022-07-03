## [《从0开始深入学习Spring》](https://s.juejin.cn/ds/YGA3GYc/) ，点击查看原书

Spring是一个开源的企业级Java开发框架，可以更容易的构建出Java应用，
并且可以根据应用开发的组件需要进行整合（容器：管理应用中使用的组件Bean、托管Bean的生命周期、事件与监听器的驱动），
它的核心是IOC和AOP，它的强大之处还体现在对事务的控制上。

### ioc_easy

![](images/ioc_easy/ioc-1.jpg)

IOC的实现有两种方式: 依赖查找、依赖注入

- 依赖查找主动获取Bean

`byName`或`byClass`或`ofType（获取某类型的所有Bean）`或`获取带有指定注解的Bean`

- 依赖注入像是被动的接收依赖的Bean

`setter注入`、`构造器注入`，像@Value注解注入也是setter注入

**【面试题】BeanFactory与ApplicationContext的对比**

BeanFactory能够进行配置和对Bean进行管理，提供了基本的API，ApplicationContext是它的子接口，也是对它的扩展。ApplicationContext支持AOP，
体现在Bean和BeanFactory的后置处理器上， 除此之外还有国际化、事件驱动（ApplicationEvent 、ApplicationListener）和资源管理（Resource）等。

`@PropertySource("classpath:cat.properties")`我们指定的properties 文件，它加载到 SpringFramework 的 IOC 容器后，
会转换成 Map 的形式来保存这些配置，而 SpringFramework 中本身在初始化时就有一些配置项，这些配置项也都放在这个 Map 中，
**${}** 的取值就是从这些配置项中取。

SpEL是真的强大，它的占位符是 **#{}**,在里边儿可以执行方法，指定值或者拿别的bean的值
