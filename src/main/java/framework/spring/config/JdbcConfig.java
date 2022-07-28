package framework.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import framework.spring.postprocessor.DataSourceRegisterPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

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

    /**
     * 编程式事务实现
     */
    @Bean
    @SuppressWarnings("all")
    public TransactionTemplate transactionTemplate(DruidDataSource druidDataSource) {
        // 基于数据源的事务管理器
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(druidDataSource);

        return new TransactionTemplate(transactionManager);
    }
}
