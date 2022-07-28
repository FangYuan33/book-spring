package framework.spring;

import framework.spring.config.DataSourceConfig;
import framework.spring.dao.impl.AccountDaoImpl;
import framework.spring.dao.impl.UserDaoImpl;

import framework.spring.pojo.User;
import framework.spring.service.AccountService;
import framework.spring.service.UserService;
import framework.spring.service.impl.AccountServiceImpl;
import framework.spring.service.impl.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcTemplateQuickstartApplication {
    public static void main(String[] args) {
        declarativeTransaction();
    }

    private static void declarativeTransaction() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DataSourceConfig.class, AccountDaoImpl.class, AccountServiceImpl.class);
        context.refresh();

        AccountService accountService = context.getBean(AccountService.class);
        accountService.transfer(1, 2, 100);
    }

    private static void programmaticTransaction() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DataSourceConfig.class, UserDaoImpl.class, UserServiceImpl.class);
        context.refresh();

        UserService userService = context.getBean(UserService.class);
        User user = new User();
        user.setName("Mountain");
        user.setTel("river");

        userService.saveAndQuery(user);
    }
}
