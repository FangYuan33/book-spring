package framework.spring.postprocessor;

import framework.spring.pojo.Waiter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TavernBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("拦截到Bean初始化之前: " + bean);

        if (bean instanceof Waiter) {
            Waiter waiter = (Waiter) bean;
            if (waiter.getName() == null) {
                waiter.setName("可乐儿");
            }
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("拦截到Bean初始化之后: " + bean);

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
