## Spring-Dao

### 1. 事务
**事务是一组逻辑操作的组合**，它们执行的结果要么全部成功，要么全部失败

事务的4个特性：
1. **原子性**: **一个事务是不可再分的单**位，事务中的操作要么全部做，要么全部不做，原子性强调的是事务的整体
2. **一致性**: 事务执行后，所有的数据都应该保持一致状态，**强调数据的完整**
3. **隔离性**: 多个数据库操作并发执行时，一个请求的事务操作不能被其它操作干扰，多个并发事务执行之间要相互隔离。**隔离性强调的是并发的隔离**
4. **持久性**: 事务执行完成后，它对数据的影响是永久性的。**持久性强调的是操作的结果**

事务**并发操作**中会出现三种问题：
1. **脏读**：一个事务读到了另一个事务没有提交的数据
2. **不可重复读**：一个事务读到了另一个事务已提交修改的数据
   - 对同一行数据查询两次，结果不一致
3. **幻读**：一个事务读到了另一个事务已提交新增的数据
   - 对同一张表查询两次，出现新增的行，导致结果不一致

针对上述问题，引出了**事务的隔离级别**：
1. **read uncommitted**: 未提交读 —— 不解决任何问题
2. **read committed**: 提交读 —— **解决脏读**
3. **repeatable read**: 可重复读 —— **解决脏读、不可重复读**
4. **serializable**: 可串行化 —— **解决脏读、不可重复读、幻读**

### 2. 编程式事务
#### 2.1 DataSourceTransactionManager
**基于数据源的事务管理器**。它实现的根接口 `PlatformTransactionManager` 有定义 `commit` 和 `rollback` 方法

#### 2.2 TransactionTemplate
它提交和回滚事务的动作，就是拿的 `TransactionManager` 执行的 `commit` 和 `rollback` 方法

### 3. 声明式事务
#### 3.1 @Transactional

- **isolation**：事务隔离级别, **默认是 DEFAULT**，即依据数据库默认的事务隔离级别来定
- **timeout**：事务超时时间，当事务执行超过指定时间后，事务会自动中止并回滚，单位 秒 。默认值 -1 ，代表永不超时
- **readOnly**：设置是否为只读事务。默认值 false ，代表**读写型事务**
   - 当设置为 true 时，当前事务为只读事务，通常用于查询操作（此时不会有 setAutoCommit(false) 等操作，可以加快查询速度）
  
- **rollbackFor**：当方法触发指定异常时，事务回滚，需要传入异常类的全限定名
   - 默认值为空，代表捕捉所有 RuntimeException 和 Error 的子类
   - 一般情况下，在日常开发中，我们都会显式声明其为 Exception，**目的是一起捕捉非运行时异常**

- **noRollbackFor**：当方法触发指定异常时，事务不回滚继续执行，需要传入异常类的全限定名。默认值为空，代表不忽略异常
- **propagation**：事务传播行为

### 4. 事务传播行为
**事务的传播** + **传播的行为**

什么叫事务的传播，必须得形成**事务的嵌套**，什么叫事务的嵌套，就是**一个事务方法调用另一个事务方法**，形成嵌套，传播的行为是传播的7种策略

**外层的事务传播到内层的事务后，内层的事务根据内层的事务策略作出的行为**，这就是事务传播行为

#### 4.1 事务传播行为的7种策略

- **REQUIRED**: **默认值**,**如果当前没有事务运行，则会开启一个新的事务；如果当前已经有事务运行，则方法会运行在当前事务中**
- **REQUIRES_NEW**: **如果当前没有事务运行，则会开启一个新的事务；如果当前已经有事务运行，则会将原事务挂起（暂停），
  重新开启一个新的事务。当新的事务运行完毕后，再将原来的事务释放。** 不论如何，都要一个新的事务
  
- **SUPPORTS**: **如果当前有事务运行，则方法会运行在当前事务中；如果当前没有事务运行，则不会创建新的事务（即不运行在事务中）**
- **NOT_SUPPORTED**: **如果当前有事务运行，则会将该事务挂起（暂停）；如果当前没有事务运行，则它也不会运行在事务中**
- **MANDATORY**: **当前方法必须运行在事务中，如果没有事务，则直接抛出异常**
- **NEVER**: **当前方法不允许运行在事务中，如果当前已经有事务运行，则抛出异常**
- **NESTED**: **如果当前没有事务运行，则开启一个新的事务；如果当前已经有事务运行，则会记录一个保存点，并继续运行在当前事务中。
  如果子事务运行中出现异常，则不会全部回滚，而是回滚到上一个保存点**

### 5. Spring事务的三大核心
SpringFramework 的事务控制模型，实际上是三个最顶层的接口：
1. **PlatformTransactionManager**：平台事务管理器
2. **TransactionDefinition**：事务定义

事务定义，或者叫事务属性，类似于Bean的 `BeanDefinition`，其中包含了在 `@Transactional` 注解中的属性
- 事务隔离级别
- 事务传播行为
- 是否为读写事务
- 超时时间
- ...

3. **TransactionStatus**：事务状态

这里边儿记录的是当前事务的运行状态：
- 是否是一个全新的事务
- 是否有保存点
- 事务是否完成
- ...

它是 SpringFramework 内部用来控制事务的封装的，开发用不到

#### 5.1 PlatformTransactionManager

平台事务管理器，这个接口中有如下三个方法

```java
public interface PlatformTransactionManager extends TransactionManager {
    
	TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;
    
	void commit(TransactionStatus status) throws TransactionException;
    
	void rollback(TransactionStatus status) throws TransactionException;
}
```

- **getTransaction**: 传入 `TransactionDefinition` ，返回 `TransactionStatus` ，很明显它是根据一个事务的定义信息，查询事务当前的状态
- **commit**：提交事务，它需要传入事务当前的状态，来判断当前事务是否允许提交
- **rollback**：回滚事务，它也需要传入事务当前的状态，以此判断当前事务是否允许回滚

**SpringFramework 事务控制的核心思路**: 根据 `TransactionDefinition` 去 `PlatformTransactionManager` 中查询事务当前的状态，
并可以依据此状态来决定事务的最终提交或回滚

#### 5.2 PlatformTransactionManager的层级关系

![img.png](img.png)

##### 5.2.1 ResourceTransactionManager
基于资源的事务管理器，看它的实现类我们可以推测出，这个"资源"是数据库的数据源

##### 5.2.2 DataSourceTransactionManager
**用 jdbc 或者 MyBatis 作为持久层框架，都是配置它作为事务管理器的实现**。它内部就**组合**了一个 DataSource

#### 5.3 TransactionDefinition

![img_1.png](img_1.png)

##### 5.3.1 TransactionAttribute

```java
public interface TransactionAttribute extends TransactionDefinition {
    String getQualifier();
    
    boolean rollbackOn(Throwable ex);
}
```
其中`rollbackOn方法`会判断事务定义中遇到**指定的异常**是否通知事务管理器回滚，
它是支撑 `@Transactional 注解`中 `rollbackFor属性`的实现

在 `DefaultTransactionAttribute` 这个实现类中，体现的便是对默认异常捕捉的实现，如下
```java
    public boolean rollbackOn(Throwable ex) {
        return (ex instanceof RuntimeException || ex instanceof Error);
    }
```

##### 5.3.2 TransactionTemplate
`TransactionTemplate`也实现了 `TransactionDefinition`，`TransactionTemplate` 的构建，需要传入**事务管理器**，
**由事务管理器来控制事务的提交和回滚**，由此联想到事务管理器接口的三个方法，
因为 `TransactionTemplate` 是 `TransactionDefinition` 的实现类，那么就可以根据它来获取事务的状态，
有了事务的状态，那么便能判断是否提交、回滚

#### 5.4 TransactionStatus
![img_2.png](img_2.png)

`DefaultTransactionStatus` 是事务底层主要使用的 `TransactionStatus` 的实现类，
SpringFramework 的事务控制中大多都是用 `DefaultTransactionStatus` 记录事务状态信息。

### 6. 声明式事务的生效原理
#### 6.1 @EnableTransactionManagement

- **proxyTargetClass**: 默认为false，代表有接口便使用jdk动态代理，无接口使用Cglib动态代理，如果改为true，那么都使用Cglib动态代理
- mode: 不常用，默认配置为PROXY运行期增强，或者选择ASPECT类加载时期增强
- order: 指定事务通知的执行顺序

#### 6.2 TransactionManagementConfigurationSelector

启用事务管理注解导入的核心选择器，如下
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TransactionManagementConfigurationSelector.class)
public @interface EnableTransactionManagement {
    ...
}
```

它会根据mode配置，注入 `AutoProxyRegistrar`， `ProxyTransactionManagementConfiguration` 两个组件

##### 6.2.1 AutoProxyRegistrar

在这个组件中注册了一个 `InfrastructureAdvisorAutoProxyCreator` 的bean，**它是AOP的代理创建器**，
而且**只关心ROLE为** `ROLE_INFRASTRUCTURE` 的 `BeanDefinition`

##### 6.2.2 ProxyTransactionManagementConfiguration

它在其中会配置三个组件，**transactionAttributeSource事务配置源**，**transactionInterceptor事务拦截器**和**transactionAdvisor事务增强器**

- transactionAttributeSource事务配置源

其中有 `getTransactionAttribute方法`，可以根据 一个**具体类**中的**方法**，将其解析转换为 `TransactionDefinition` (`TransactionAttribute`)

创建的`TransactionAttributeSource`的实现类是 `AnnotationTransactionAttributeSource`，
**作用是读取和解析标注有 `@Transactional` 注解的方法**

- transactionAdvisor事务增强器

它的实现类是 `BeanFactoryTransactionAttributeSourceAdvisor`，
其中组合了 **TransactionAttributeSource事务配置源**和**TransactionInterceptor事务拦截器**

`TransactionAttributeSourcePointcut`根据 `TransactionAttributeSource`来判断，其中核心`matches方法`如下
```java
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		TransactionAttributeSource tas = getTransactionAttributeSource();
		return (tas == null || tas.getTransactionAttribute(method, targetClass) != null);
	}
```
它就是拿 TransactionAttributeSource 去根据方法和方法所属类，判断是否有对应的事务定义信息（是否被 @Transactional 注解标注）

