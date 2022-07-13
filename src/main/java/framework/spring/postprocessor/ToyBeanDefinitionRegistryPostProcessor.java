package framework.spring.postprocessor;

import framework.spring.pojo.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * 注册BeanDefinition的后置处理器
 * 实现了对car BeanDefinition 的注册
 */
public class ToyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (!registry.containsBeanDefinition("car")) {
            AbstractBeanDefinition carBeanDefinition =
                    BeanDefinitionBuilder.genericBeanDefinition(Car.class).getBeanDefinition();

            registry.registerBeanDefinition("car", carBeanDefinition);
        }
    }

    /**
     * bean factory的方法
     * 这个方法在执行完上边儿的注册BeanDefinition的方法后执行
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
