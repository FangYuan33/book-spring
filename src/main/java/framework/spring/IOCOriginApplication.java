package framework.spring;

import framework.spring.config.LifecycleBeanConfiguration;
import framework.spring.pojo.Dog;
import framework.spring.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCOriginApplication {

    public static void main(String[] args) {
        xmlBeanDefinition();
    }

    private static void xmlBeanDefinition() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("listable-container.xml");
        context.refresh();

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(LifecycleBeanConfiguration.class, Dog.class);
        annotationConfigApplicationContext.refresh();
    }

    private static void lifecycle() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LifecycleBeanConfiguration.class, Dog.class);

        System.out.println("准备刷新IOC容器");
        context.refresh();
        System.out.println("IOC容器刷新完毕");

        System.out.println("准备启动IOC容器");
        context.start();
        System.out.println("IOC容器启动完毕");

        Person person = context.getBean(Person.class);
        System.out.println(person);
        Dog dog = context.getBean(Dog.class);
        System.out.println(dog);

        System.out.println("准备停止IOC容器");
        context.stop();
        System.out.println("IOC容器停止完毕");

        System.out.println("准备关闭IOC容器");
        context.close();
        System.out.println("IOC容器关闭完毕");
    }
}
