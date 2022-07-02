package framework.spring;

import framework.spring.basic_dl.Cat;
import framework.spring.basic_dl.Person;
import framework.spring.service.DemoService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuickstartApplication {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("basic_dl/quickstart-byname.xml");

        // byName
        Person personByName = (Person) factory.getBean("person");
        System.out.println(personByName);

        // byClass
        Cat cat = factory.getBean(Cat.class);
        System.out.println(cat);

        DemoService demoService = factory.getBean(DemoService.class);
        System.out.println(demoService.findAll());
    }
}
