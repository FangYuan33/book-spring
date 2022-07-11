package framework.spring;

import framework.spring.config.BasePackageClassConfiguration;
import framework.spring.moduleimport.TavernConfiguration;
import framework.spring.pojo.Person;
import framework.spring.resolver.DogProtocolResolver;
import framework.spring.service.impl.RegisterService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class IOCMediumApplication {

    public static void main(String[] args) throws IOException {
        protocolResolver();
    }

    private static void componentScan() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasePackageClassConfiguration.class);

        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    private static void protocolResolver() throws IOException {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        DogProtocolResolver dogProtocolResolver = new DogProtocolResolver();
        defaultResourceLoader.addProtocolResolver(dogProtocolResolver);

        Resource resource = defaultResourceLoader.getResource("dog:dog.txt");
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            System.out.println(readLine);
        }
        bufferedReader.close();
    }

    private static void moduleImport() {
        // 这样指定profile-city是不能生效的，因为传入了配置类，它在内部会自动初始化完成且仅仅执行一次refresh方法，所以指定的profile不能生效
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TavernConfiguration.class);
//        context.getEnvironment().setActiveProfiles("city");

        // 这样就ok了
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.getEnvironment().setActiveProfiles("city");
        context.register(TavernConfiguration.class);
        context.refresh();

        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    private static void listener() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "framework.spring.listener",
                "framework.spring.service.impl");
        System.out.println("容器初始化完成...");

        RegisterService registerService = context.getBean(RegisterService.class);
        registerService.register("FangYuan");

        context.close();
        System.out.println("容器关闭...");
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
