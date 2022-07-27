package framework.spring;

import framework.spring.config.DataSourceConfig;
import framework.spring.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTemplateQuickstartApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        initialJdbcTemplate(jdbcTemplate);
    }

    private static void initialJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        jdbcTemplate.execute("insert into tbl_user (name, tel) values ('FangYuan', '12345')");

        List<User> users = jdbcTemplate.query("select * from tbl_user", new BeanPropertyRowMapper<>(User.class));
        users.forEach(System.out::println);
    }
}
