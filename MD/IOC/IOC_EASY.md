## ioc_easy

### 1. 理解IOC
![](../../images/ioc_easy/ioc-1.jpg)

IOC的实现有两种方式: **依赖查找**、**依赖注入**

- 依赖查找主动获取Bean

`byName`或`byClass`或`ofType（获取某类型的所有Bean）`或`获取带有指定注解的Bean`

- 依赖注入像是被动的接收依赖的Bean

`setter注入`、`构造器注入`，像@Value注解注入也是setter注入，`参数注入`见下图

![](../../images/ioc_easy/ioc-2.jpg)

Spring文档中的一个概念特别好：**DI 的思想是“推”**，它主张把组件需要的依赖“推”到组件的成员上；
**DL 的思想是”拉“**，组件需要哪些依赖需要组件自己去 IOC 容器中“拉取”。

### 2. 占位符和properties文件

`@PropertySource("classpath:cat.properties")`我们指定的properties 文件，它加载到 SpringFramework 的 IOC 容器后，
会转换成 **Map 的形式**来保存这些配置，而 SpringFramework 中本身在初始化时就有一些配置项，这些配置项也都放在这个 Map 中，
**${}** 的取值就是从这些配置项中取

SpEL是真的强大，它的占位符是 **#{}**,在里边儿可以执行方法，指定值或者拿别的bean的值

### 3. BeanFactory与ApplicationContext的对比

BeanFactory是Spring中最底层的接口，能够对Bean进行配置和管理，提供了基本的API，
ApplicationContext是它的子接口，也是对它的扩展。ApplicationContext支持AOP，
除此之外还有国际化、事件驱动（ApplicationEvent 、ApplicationListener）和资源管理（Resource）等

### 4. 理解@Autowired注入的原理逻辑

先拿属性对应的**类型**，去 IOC 容器中找 Bean ，如果找到了一个，直接返回；
如果找到多个类型一样的 Bean，把属性名拿过去（byName），跟这些 Bean 的 id 逐个对比`（@Qualifier）`，如果有一个相同的，直接返回；
如果没有任何相同的 id 与要注入的属性名相同，则会抛出 `NoUniqueBeanDefinitionException` 异常。

`@Resource` 注解相当于标注 @Autowired 和 @Qualifier

### 5. 依赖注入的目的和优点

依赖注入作为 IOC 的实现方式之一，目的就是**解耦**，我们不再需要直接去 new 那些依赖的类对象，而是把它交给Spring替我们管理；
而且，如果组件存在多级依赖，依赖注入可以将这些依赖的关系简化，开发者只需要定义好谁依赖谁即可。

依赖注入的另一个特点是依赖对象的**可配置**：通过 xml 或者注解声明，可以指定和调整组件注入的对象，借助 Java 的多态特性，
可以不需要大批量的修改就完成依赖注入的对象替换（面向接口编程与依赖注入配合近乎完美）。

Bean注入大部分可以用`@Autowired`来解决

但是也需要了解一下实现`Aware接口`完成bean的注入的操作，比如某个bean实现`ApplicationContextAware`接口，
那么重写的它的方法之后也能注入 `Application`，除此之外还有 `EnvironmentAware` 来注入 `Environment`

### 6. FactoryBean

Bean的类型包含**普通Bean**和**工厂Bean**，工厂Bean用来创建使用注解或xml配置创建起来比较麻烦或依赖具体业务场景的Bean，它的接口方法如下

![](../../images/ioc_easy/ioc-3.jpg)

#### 6.1 理解BeanFactory与FactoryBean的区别

`BeanFactory`：SpringFramework 中实现 IOC 的最底层容器
（此处的回答可以从两种角度出发：从类的继承结构上看，它是最顶级的接口，也就是最顶层的容器实现；
从类的组合结构上看，它则是最深层次的容器，ApplicationContext 在最底层组合了 BeanFactory ）

`FactoryBean`：**创建对象的工厂Bean**，可以使用它来直接创建一些初始化流程比较复杂的对象，
FactoryBean的创建时机是和IOC容器的初始化一起的(**生命周期与IOC容器一致**)，而**对具体的对象创建是懒加载机制**。

FactoryBean创建对象只需注入FactoryBean即可，IOC容器自动识别**创建想要的Bean**

### 7. Scope作用域

如果一个对象允许同时被多个地方访问，那么它的作用域就可以提的很高，
反之如果一个对象伴随着一个时效性强的、带强状态的动作，那这个**作用域就应该局限于这一个动作**，在不同的动作下有一个不同的对象

SpringFramework内置的作用域
- singleton: 单例bean，默认
- prototype: 原型bean，每次获取都创建一个
- request: 一次web请求创建一个
- session: 一个会话创建一个（web）
- application: 一个web应用创建一个
- websocket: 一个websocket创建一个

### 8. Bean的生命周期

![img.png](../../images/ioc_easy/img.png)

一个对象从被创建，到被垃圾回收，可以宏观的划分为 5 个阶段：

- **创建 / 实例化阶段**：此时会调用类的构造方法，产生一个新的对象
- **初始化阶段**：此时对象已经创建好，但还没有被正式使用，可能这里面需要做一些额外的操作（如预初始化数据库的连接池）
- **运行使用期**：此时对象已经完全初始化好，程序正常运行，对象被使用
- **销毁阶段**：此时对象准备被销毁，已不再使用，需要预先的把自身占用的资源等处理好（如关闭、释放数据库连接）
- **回收阶段**：此时对象已经完全没有被引用了，被垃圾回收器回收

而Spring**能进行干预的只有初始化阶段和销毁阶段**，我们可以指定`init-method`和`destroy-method`，init-method的执行时机是在属性赋值之后。
或者也可以通过JSR250规范中的注解`@PostConstruct`和`@PreDestroy`实现再或者实现`InitializingBean`或`DisposableBean`接口

这三种方式中执行的优先级`@PostConstruct` > `InitializingBean` > `init-method`

原型Bean它不会处理`destroy-method`方法
