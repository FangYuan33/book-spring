package framework.spring;

import framework.spring.config.DataSourceConfig;
import framework.spring.dao.UserDao;
import framework.spring.dao.impl.UserDaoImpl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcTemplateQuickstartApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DataSourceConfig.class, UserDaoImpl.class);
        context.refresh();

        UserDao userDao = context.getBean(UserDao.class);
        userDao.selectAll().forEach(System.out::println);
    }
}
