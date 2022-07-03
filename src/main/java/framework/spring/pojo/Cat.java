package framework.spring.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Cat {

    private String name;

    private Person master;
}
