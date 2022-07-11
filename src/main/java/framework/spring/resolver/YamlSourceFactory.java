package framework.spring.resolver;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Properties;

public class YamlSourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();

        yamlPropertiesFactoryBean.setResources(resource.getResource());

        Properties properties = yamlPropertiesFactoryBean.getObject();

        return new PropertiesPropertySource((name != null ? name : resource.getResource().getFilename()), properties);
    }
}
