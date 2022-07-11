package framework.spring.moduleimport;

import framework.spring.pojo.Bartender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("city")
public class BartenderConfiguration {

    @Bean
    public Bartender fy() {
        return new Bartender("fy");
    }

    @Bean
    public Bartender bb() {
        return new Bartender("bb");
    }
}
