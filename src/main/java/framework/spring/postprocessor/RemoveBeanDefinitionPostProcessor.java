package framework.spring.postprocessor;

import framework.spring.pojo.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

/**
 * 创建BeanFactory的后置处理器来移除BeanDefinition
 *
 * ConfigurableListableBeanFactory实现了DefaultListableBeanFactory，
 * 而DefaultListableBeanFactory又实现了BeanDefinitionRegistry，所以可以直接强转
 */
@Component
public class RemoveBeanDefinitionPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);

            if (Person.class.getName().equals(beanDefinition.getBeanClassName())) {
                TypedStringValue name = (TypedStringValue) beanDefinition.getPropertyValues().get("name");

//                if ("FYuan".equals(name.getValue())) {
//                    registry.removeBeanDefinition(beanDefinitionName);
//                }
            }
        }
    }
}
