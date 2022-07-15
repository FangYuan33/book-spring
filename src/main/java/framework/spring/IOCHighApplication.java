package framework.spring;

import com.alibaba.druid.pool.DruidDataSource;
import framework.spring.config.DataSourceConfig;
import framework.spring.config.JavaBeanConfig;
import framework.spring.dao.DemoDao;
import framework.spring.event.HierarchicalEvent;
import framework.spring.listener.HierarchicalEventListener;
import framework.spring.moduleimport.TavernConfiguration;
import framework.spring.pojo.*;
import framework.spring.postprocessor.BossInstantiationPostProcessor;
import framework.spring.postprocessor.TavernBeanPostProcessor;
import framework.spring.postprocessor.ToyBeanDefinitionRegistryPostProcessor;
import framework.spring.postprocessor.ToyBeanFactoryPostProcessor;
import framework.spring.properties.JdbcProperties;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.beans.Introspector;
import java.util.List;
import java.util.Set;

public class IOCHighApplication {

    public static void main(String[] args) {
        hierarchicalEvent();
    }

    private static void hierarchicalEvent() {
        AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
        parent.addApplicationListener(new HierarchicalEventListener());

        AnnotationConfigApplicationContext son = new AnnotationConfigApplicationContext();
        son.setParent(parent);
        son.addApplicationListener(new HierarchicalEventListener());

        parent.refresh();
        son.refresh();

        // 事件会向上传播，父容器能监听到这两条事件，子容器只有一条
        parent.publishEvent(new HierarchicalEvent("Parent事件"));
        son.publishEvent(new HierarchicalEvent("Son事件"));
    }

    private static void spi() {
        List<DemoDao> demoDaos = SpringFactoriesLoader
                .loadFactories(DemoDao.class, IOCHighApplication.class.getClassLoader());
        demoDaos.forEach(System.out::println);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        System.out.println(context.getBean(DruidDataSource.class).getUrl());
    }

    /**
     * 编程式驱动IOC
     */
    private static void programmatic() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        AbstractBeanDefinition personDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(Person.class).addPropertyValue("name", "hsy").getBeanDefinition();
//        context.registerBeanDefinition("person", personDefinition);

        AbstractBeanDefinition dogDefinition = BeanDefinitionBuilder.rootBeanDefinition(Dog.class)
                .addPropertyValue("name", "xiao hei")
                .addPropertyReference("master", "person").getBeanDefinition();
//        context.registerBeanDefinition("dog", dogDefinition);

        // 包扫描
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
        scanner.addIncludeFilter((metadataReader, metadataReaderFactory) ->
                metadataReader.getClassMetadata().getClassName().equals(Dog.class.getName()));
        scanner.addExcludeFilter((metadataReader, metadataReaderFactory) ->
                metadataReader.getClassMetadata().getClassName().equals(Cat.class.getName()));
        scanner.addExcludeFilter((metadataReader, metadataReaderFactory) ->
                metadataReader.getClassMetadata().getClassName().equals(Child.class.getName()));
        scanner.addExcludeFilter((metadataReader, metadataReaderFactory) ->
                metadataReader.getClassMetadata().getClassName().equals(Red.class.getName()));
//        scanner.scan("framework.spring.pojo");

//        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents("framework.spring.pojo");
//        beanDefinitions.forEach(definition -> {
//            MutablePropertyValues propertyValues = definition.getPropertyValues();
//            propertyValues.addPropertyValue("name", definition.getBeanClassName());
//            propertyValues.addPropertyValue("master", new RuntimeBeanReference("person"));
//
//            context.registerBeanDefinition(Introspector
//                    .decapitalize(definition.getBeanClassName().substring(definition.getBeanClassName().lastIndexOf("."))),
//                    definition);
//        });

        // 搭配XmlBeanDefinitionReader实现配置文件的bean加载
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("listable-container.xml"));

        context.refresh();
        System.out.println("Context refresh finish...");

        // FYuan 被后置处理器移除了
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    private static void beanFactoryPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ToyBeanFactoryPostProcessor.class, Ball.class, ToyBeanDefinitionRegistryPostProcessor.class);
        context.refresh();

        System.out.println(context.getBean("ball"));
        System.out.println(context.getBean("car"));
    }

    private static void instantiationAwareBeanPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(JavaBeanConfig.class, BossInstantiationPostProcessor.class);
        context.refresh();
    }

    private static void beanPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TavernBeanPostProcessor.class, TavernConfiguration.class);
        context.refresh();
    }

    private static void beanDefinitionRegistry() {
        // beanDefinition的注册
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TavernConfiguration.class);
        System.out.println(context.getBean("。"));

        // beanDefinition的删除
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("listable-container.xml");
        System.out.println(xmlContext.getBean(Person.class));
    }

    private static void beanDefinition() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("listable-container.xml");

        // xml配置
        BeanDefinition person = context.getBeanFactory().getBeanDefinition("person");
        System.out.println(person);

        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(JavaBeanConfig.class);

        // 注解java配置
        BeanDefinition javaBeanConfig = annotationContext.getBeanFactory().getBeanDefinition("javaBeanConfig");
        System.out.println(javaBeanConfig);

        // @Bean注解配置
        BeanDefinition me = annotationContext.getBeanFactory().getBeanDefinition("me");
        System.out.println(me);
    }

    private static void environment() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcProperties.class);

        Environment environment = context.getBean(Environment.class);
        System.out.println(environment);

        System.out.println(environment.getProperty("jdbc.url"));

        StringBuilder profiles = new StringBuilder();
        for (String defaultProfile : environment.getDefaultProfiles()) {
            profiles.append(defaultProfile).append(" ");
        }
        System.out.println("Default profile: " + profiles);
    }
}
