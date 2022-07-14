package framework.spring.postprocessor;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.sql.Driver;
import java.util.List;

/**
 * 写了一个后置处理器来统一处理 DataSource生成的冗余代码的问题
 */
public class DataSourceRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionBuilder dataSourceBuilder = BeanDefinitionBuilder.rootBeanDefinition(DruidDataSource.class)
                .addPropertyValue("url", environment.getProperty("jdbc.url"))
                .addPropertyValue("username", environment.getProperty("jdbc.username"))
                .addPropertyValue("password", environment.getProperty("jdbc.password"));

        List<String> driverClassNames = SpringFactoriesLoader
                .loadFactoryNames(Driver.class, this.getClass().getClassLoader());

        // 根据已有的驱动找到真正的要使用的数据库驱动
        String realDriverClassName = null;
        for (String className : driverClassNames) {
            try {
                Class.forName(className);
                realDriverClassName = className;
            } catch (Exception e) {
                System.out.println(className + " 没有");
            }
        }

        if (realDriverClassName != null) {
            dataSourceBuilder.addPropertyValue("driverClassName", realDriverClassName);
            registry.registerBeanDefinition("dataSource", dataSourceBuilder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
