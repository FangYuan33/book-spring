package framework.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.stream.Stream;

/**
 * 了解回调注入 ApplicationContext
 */
public class TestApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void printBeanNames() {
        Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
