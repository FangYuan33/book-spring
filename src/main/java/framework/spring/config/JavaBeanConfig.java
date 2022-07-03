package framework.spring.config;

import framework.spring.aware.TestApplicationContextAware;
import framework.spring.factory.CarStaticFactoryBean;
import framework.spring.pojo.Car;
import framework.spring.pojo.Cat;
import framework.spring.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:cat.properties")
@ComponentScan(basePackages = "framework.spring")
public class JavaBeanConfig {

    @Bean
    public Person me() {
        Person me = new Person();
        me.setAge(23);
        me.setName("Turing");

        return me;
    }

    @Bean(value = "Miao", initMethod = "init", destroyMethod = "destroy1")
    public Cat cat(Person person) {
        Cat cat = new Cat();
        cat.setMaster(person);
        cat.setName("Miao");

        return cat;
    }

    @Bean
    public TestApplicationContextAware testApplicationContextAware() {
        return new TestApplicationContextAware();
    }

    @Bean
    public Car car() {
        return CarStaticFactoryBean.getCar();
    }
}
