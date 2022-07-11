package framework.spring.config;

import framework.spring.annotation.Animal;
import framework.spring.annotation.Color;
import framework.spring.pojo.Red;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 扫描该类以及该类所在的包和子包内所有标注@Conmponent注解的Bean
 */
@Configuration
@ComponentScan(
        basePackageClasses = Red.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Animal.class),
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Color.class))
public class BasePackageClassConfiguration {
}
