package framework.spring.annotation;

import framework.spring.config.JdbcConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(JdbcConfig.class)
public @interface EnableJdbc {
}
