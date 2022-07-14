package framework.spring.annotation;

import framework.spring.condition.OnClassNameConditional;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 这个注解的功能是能够根据指定的class name来选择性注入bean
 * 结合了Conditional注解的功能
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnClassNameConditional.class)
public @interface ConditionOnClassName {

    /**
     * 指定class name 条件注入
     */
    String value();
}
