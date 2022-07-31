package framework.spring.config;

import framework.spring.annotation.EnableJdbc;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJdbc
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource(value = "classpath:database.properties")
public class DataSourceConfig {
}
