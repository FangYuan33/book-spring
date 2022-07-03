package framework.spring.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Cat {

    @Value("${name}")
    private String name;

    private Person master;
}
