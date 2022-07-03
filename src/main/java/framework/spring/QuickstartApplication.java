package framework.spring;

import framework.spring.annotation.Color;
import framework.spring.config.JavaBeanConfig;
import framework.spring.factory.BeanFactory;
import framework.spring.pojo.Cat;
import framework.spring.pojo.Person;
import framework.spring.dao.DemoDao;
import framework.spring.pojo.Red;
import framework.spring.service.DemoService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.stream.Stream;

public class QuickstartApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("basic_dl/quickstart-byname.xml");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaBeanConfig.class);
        spELPractice(ctx);
    }

    private static void spELPractice(ApplicationContext ctx) {
        Red red = ctx.getBean(Red.class);

        System.out.println(red);
    }

    private static void javaConfigGetBean(ApplicationContext ctx) {
        Person me = ctx.getBean(Person.class);
        System.out.println(me);

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);

        Cat cat = (Cat) ctx.getBean("cat");
        System.out.println(cat);
    }

    private static void beanProvider(ApplicationContext applicationContext) {
        // 延迟查找，这个BeanFactory的类我们没有在配置中定义，所以获取不到，但是有了Provider不会因此而抛出异常
        ObjectProvider<BeanFactory> beanProvider = applicationContext.getBeanProvider(BeanFactory.class);
        BeanFactory availableBeanFactory = beanProvider.getIfAvailable(BeanFactory::new);
        System.out.println(availableBeanFactory);
    }

    private static void getBeanDefinitionNames(ApplicationContext applicationContext) {
        // 获取所有定义的Bean的ID
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }

    private static void getBeansWithAnnotation(ApplicationContext applicationContext) {
        // 获取带有指定注解的bean
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Color.class);
        System.out.println(beansWithAnnotation);
    }

    private static void ofType(ApplicationContext applicationContext) {
        // ofType 获取多个同一个类的Bean
        Map<String, DemoDao> daoBeans = applicationContext.getBeansOfType(DemoDao.class);
        System.out.println(daoBeans);
    }

    private static void byClass(ApplicationContext applicationContext) {
        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println(demoService.findAll());
    }

    private static void byName(ApplicationContext applicationContext) {
        Person personByName = (Person) applicationContext.getBean("person");
        System.out.println(personByName);
    }
}
