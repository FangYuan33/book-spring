package framework.spring;

import framework.spring.basic_dl.Person;
import framework.spring.dao.DemoDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuickstartApplication {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("basic_dl/quickstart-byname.xml");

        // byName
        Person personByName = (Person) factory.getBean("person");
        System.out.println(personByName);

        // byClass
        Person personByClass = factory.getBean(Person.class);
        System.out.println(personByClass);

        DemoDao demoDao = factory.getBean(DemoDao.class);
        System.out.println(demoDao.findAll());
    }
}
