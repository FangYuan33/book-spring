package framework.spring.config;

import framework.spring.annotation.EnableJdbc;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

public class JdbcConfigSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> configClazzNames =
                SpringFactoriesLoader.loadFactoryNames(EnableJdbc.class, this.getClass().getClassLoader());

        return configClazzNames.toArray(new String[0]);
    }
}
