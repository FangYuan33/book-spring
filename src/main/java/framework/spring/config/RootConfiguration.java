package framework.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

/**
 * 父容器的配置类 service and repository
 */
@Configuration
@Import(JdbcConfig.class)
@ComponentScan(value = "framework.spring",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        })
public class RootConfiguration {
}
