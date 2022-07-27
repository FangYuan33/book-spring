package framework.spring;

import framework.spring.config.DataSourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateQuickstartApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        jdbcTemplate.execute("insert into tbl_user (name, tel) values ('FangYuan', '12345')");
    }
}
