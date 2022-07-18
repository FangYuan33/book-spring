package framework.spring.config;

import framework.spring.pojo.Person;
import framework.spring.postprocessor.LifecycleDestructionPostProcessor;
import framework.spring.postprocessor.LifecyclePostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.PriorityOrdered;

@Configuration
public class LifecycleBeanConfiguration {

    public LifecycleBeanConfiguration() {
        System.out.println("LifecycleBeanConfiguration Constructor RUN...");
    }

    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("Fy");

        return person;
    }

    @Bean
    public LifecyclePostProcessor lifecyclePostProcessor() {
        return new LifecyclePostProcessor();
    }

    @Bean
    public LifecycleDestructionPostProcessor lifecycleDestructionPostProcessor() {
        return new LifecycleDestructionPostProcessor();
    }
}
