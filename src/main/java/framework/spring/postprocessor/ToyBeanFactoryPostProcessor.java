package framework.spring.postprocessor;

import framework.spring.pojo.base.Toy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * Toy抽象类的BeanDefinition后置去处理器
 * 为toy抽象父类的字段赋值，让子类都有一个默认值
 */
public class ToyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);

            if (StringUtils.hasText(beanDefinition.getBeanClassName())) {
                if (ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), this.getClass().getClassLoader())
                        .getSuperclass().equals(Toy.class)) {
                    beanDefinition.getPropertyValues().add("realName", beanDefinitionName);
                }
            }
        }
    }
}
