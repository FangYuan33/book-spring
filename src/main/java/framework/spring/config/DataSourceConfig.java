package framework.spring.config;

import framework.spring.annotation.EnableJdbc;
import org.springframework.context.annotation.PropertySource;

@EnableJdbc
@PropertySource(value = "classpath:database.properties")
public class DataSourceConfig {
}
