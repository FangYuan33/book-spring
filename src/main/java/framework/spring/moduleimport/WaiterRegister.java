package framework.spring.moduleimport;

import framework.spring.pojo.Waiter;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * BeanDefinitionRegistry注册BeanDefinition
 */
public class WaiterRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // id, waiter beanDefinition
        registry.registerBeanDefinition("waiter", new RootBeanDefinition(Waiter.class));

        registry.registerBeanDefinition("。",
                BeanDefinitionBuilder.genericBeanDefinition(Waiter.class)
                        .addPropertyValue("name", "。").getBeanDefinition());
    }
}
