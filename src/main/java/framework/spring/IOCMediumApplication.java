package framework.spring;

import framework.spring.pojo.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.stream.Stream;

public class IOCMediumApplication {

    public static void main(String[] args) {
        listableBeanFactoryCurrentBeanFactory();
    }

    /**
     * listableBeanFactory只会列举当前容器中的Bean
     */
    private static void listableBeanFactoryCurrentBeanFactory() {
        ClassPathResource resource = new ClassPathResource("listable-container.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions(resource);

        // 直接打印容器中的所有Bean
        System.out.println("加载xml文件后容器中的Bean：");
        Stream.of(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);

        beanFactory.registerSingleton("person3", new Person());
        // 再打印容器中的所有Bean
        System.out.println("手动注册单实例Bean后容器中的所有Bean：");
        Stream.of(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);

        System.out.println("容器中真的有注册person3：" + beanFactory.getBean("person3"));
    }
}
