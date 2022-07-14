package framework.spring.config;

import framework.spring.annotation.EnableJdbc;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

/**
 * 模块注入的一种实现方式，根据多个类名来注入多个bean
 */
public class JdbcConfigSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> configClazzNames =
                SpringFactoriesLoader.loadFactoryNames(EnableJdbc.class, this.getClass().getClassLoader());

        return configClazzNames.toArray(new String[0]);
    }
}
