package framework.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import framework.spring.postprocessor.DataSourceRegisterPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
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
     * 基于数据源的事务管理器
     */
    @Bean
    @SuppressWarnings("all")
    public TransactionManager transactionManager(DruidDataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }

    /**
     * 编程式事务实现
     */
    @Bean
    public TransactionTemplate transactionTemplate(TransactionManager transactionManager) {
        return new TransactionTemplate((DataSourceTransactionManager) transactionManager);
    }
}
