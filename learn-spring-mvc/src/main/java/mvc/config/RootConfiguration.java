package mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 父容器的配置类 service and repository
 */
@Configuration
@ComponentScan(value = "mvc")
public class RootConfiguration {
}
