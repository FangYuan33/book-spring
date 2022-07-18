package framework.spring.postprocessor;

import framework.spring.pojo.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LifecyclePostProcessor implements BeanPostProcessor {
    public LifecyclePostProcessor() {
        System.out.println("LifecyclePostProcessor Constructor RUN...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            Person person = (Person) bean;

            System.out.println("Lifecycle before initialization run: " + person.getName());
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            Person person = (Person) bean;

            System.out.println("Lifecycle after initialization run: " + person.getName());
        }

        return bean;
    }
}
