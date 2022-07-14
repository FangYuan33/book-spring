package framework.spring.annotation;

import framework.spring.condition.OnClassNameConditional;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnClassNameConditional.class)
public @interface ConditionOnClassName {

    String value();
}
