package framework.spring.config;

import framework.spring.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "framework.spring")
public class JavaBeanConfig {

    @Bean
    public Person me() {
        Person me = new Person();
        me.setAge(23);
        me.setName("Turing");

        return me;
    }
}
