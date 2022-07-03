package framework.spring.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Cat {

    @Value("${name}")
    private String name;

    private Person master;

    /**
     * value注入多个Bean，用SpEL两个大括号接收
     */
    @Value("#{{red, me, javaBeanConfig}}")
    private List<Object> beans;
}
