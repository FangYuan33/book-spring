package framework.spring.config;

import framework.spring.service.impl.DemoServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描该类以及该类所在的包和子包内所有标注@Conmponent注解的Bean
 */
@Configuration
@ComponentScan(basePackageClasses = DemoServiceImpl.class)
public class BasePackageClassConfiguration {
}
