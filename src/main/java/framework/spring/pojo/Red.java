package framework.spring.pojo;

import framework.spring.annotation.Color;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Color
@Component
public class Red {

    @Value("#{T(java.lang.Integer).MAX_VALUE}")
    private Integer num;

    @Value("#{cat.name}")
    private String str;
}
