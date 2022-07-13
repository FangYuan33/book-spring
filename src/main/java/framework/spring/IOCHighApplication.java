package framework.spring;

import framework.spring.config.JavaBeanConfig;
import framework.spring.moduleimport.TavernConfiguration;
import framework.spring.pojo.Person;
import framework.spring.postprocessor.BossInstantiationPostProcessor;
import framework.spring.postprocessor.TavernBeanPostProcessor;
import framework.spring.properties.JdbcProperties;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class IOCHighApplication {

    public static void main(String[] args) {
        instantiationAwareBeanPostProcessor();
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
