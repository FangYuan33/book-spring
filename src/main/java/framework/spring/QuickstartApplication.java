package framework.spring;

import framework.spring.basic_dl.Cat;
import framework.spring.basic_dl.Person;
import framework.spring.dao.DemoDao;
import framework.spring.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class QuickstartApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("basic_dl/quickstart-byname.xml");

        // byName
        Person personByName = (Person) applicationContext.getBean("person");
        System.out.println(personByName);

        // byClass
        Cat cat = applicationContext.getBean(Cat.class);
        System.out.println(cat);

        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println(demoService.findAll());

        // ofType 获取多个同一个类的Bean
        Map<String, DemoDao> daoBeans = applicationContext.getBeansOfType(DemoDao.class);
        System.out.println(daoBeans);
    }
}
