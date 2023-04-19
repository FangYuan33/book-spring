## [《从0开始深入学习Spring》](https://s.juejin.cn/ds/YGA3GYc/)

Spring是一个开源的企业级Java开发框架，可以更容易的构建出Java应用，
并且可以根据应用开发的组件需要进行整合（容器：管理应用中使用的组件Bean、托管Bean的生命周期、事件与监听器的驱动），
它的核心是IOC和AOP，它的强大之处还体现在对事务的控制上。

在 SpringFramework 的框架编码中，如果有出现一个方法是 **do 开头**，**并且去掉 do 后能找到一个与剩余名称一样的方法**，
则代表如下含义：**不带 do 开头的方法一般负责前置校验处理、返回结果封装**，**带 do 开头的方法是真正执行逻辑的方法**
（ 如 getBean 方法的底层会调用 doGetBean 来真正的寻找 IOC 容器的 bean ，createBean 会调用 doCreateBean 来真正的创建一个 bean ）。

Spring的模块划分
- beans、core、context、expression 【核心包、容器】
- aop【切面编程】
- jdbc【整合jdbc】
- orm【整合orm框架】
- tx【事务控制】
- web【web层技术】
- test【整合测试】
- ...

### 1. IOC 部分笔记

- [IOC入门](MD/IOC/IOC_EASY.md)
- [IOC进阶](MD/IOC/IOC_MEDIUM.md)
- [IOC高级](MD/IOC/IOC_HIGH.md)
- [IOC原理](MD/IOC/IOC_ORIGIN.md)

### 2. AOP 部分笔记

- [AOP入门](MD/AOP/AOP_EASY.md)
- [AOP原理](MD/AOP/AOP_ORIGIN.md)

### 3. Spring-DAO 事务笔记

- [Spring-DAO 事务](MD/DAO/SPRING_DAO.md)

### 4. Spring-MVC 笔记

- [Spring-MVC](MD/MVC/MVC.md)

### 5. SpringBoot自动装配

- [SpringBoot 自动装配](MD/SPRINGBOOT/SPRINGBOOT.md)

### 6. Lifecycle

- [Spring Lifecycle](MD/LIFECYCLE/LIFECYCLE.md)
