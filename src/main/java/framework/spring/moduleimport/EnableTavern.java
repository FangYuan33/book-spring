package framework.spring.moduleimport;

import framework.spring.pojo.Boss;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({Boss.class, BartenderConfiguration.class, BarImportSelector.class, WaiterRegister.class})
public @interface EnableTavern {
}
