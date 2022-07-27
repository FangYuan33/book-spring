package framework.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import framework.spring.postprocessor.DataSourceRegisterPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSourceRegisterPostProcessor dataSourceRegisterPostProcessor() {
        return new DataSourceRegisterPostProcessor();
    }

    @Bean
    @SuppressWarnings("all")
    public JdbcTemplate jdbcTemplate(DruidDataSource druidDataSource) {
        return new JdbcTemplate(druidDataSource);
    }
}
