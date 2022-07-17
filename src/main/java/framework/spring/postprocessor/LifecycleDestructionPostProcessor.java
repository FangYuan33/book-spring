package framework.spring.postprocessor;

import framework.spring.pojo.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

public class LifecycleDestructionPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (bean instanceof Dog) {
            Dog dog = (Dog) bean;

            System.out.println("Lifecycle destruction post processor run: " + dog.getName());
        }
    }
}
