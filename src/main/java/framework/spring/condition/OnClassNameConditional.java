package framework.spring.condition;

import framework.spring.annotation.ConditionOnClassName;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 根据类名条件注入的实现类，之后放到@Conditional注解中就行
 */
public class OnClassNameConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            String value = (String) metadata.getAnnotationAttributes(ConditionOnClassName.class.getName()).get("value");
            Class.forName(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
