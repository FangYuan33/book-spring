package framework.spring;

import framework.spring.basic_dl.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuickstartApplication {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("basic_dl/quickstart-byname.xml");

        Person person = (Person) factory.getBean("person");

        System.out.println(person);
    }
}
