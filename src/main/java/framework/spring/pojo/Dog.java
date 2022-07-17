package framework.spring.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Dog {

    @Value("Wang Wang")
    private String name;

    @Autowired
    private Person master;

    public Dog() {
        System.out.println("Dog constructor RUN...");
    }
}
