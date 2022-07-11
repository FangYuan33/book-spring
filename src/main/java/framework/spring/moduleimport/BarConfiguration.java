package framework.spring.moduleimport;

import framework.spring.condition.ExistBossCondition;
import framework.spring.pojo.Bar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarConfiguration {

    @Bean
    @Conditional(ExistBossCondition.class)
    public Bar bar() {
        return new Bar();
    }
}
