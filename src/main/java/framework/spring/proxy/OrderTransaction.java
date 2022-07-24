package framework.spring.proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * order注解 或 实现 ordered接口来定义切面的执行顺序
 */
@Aspect
//@Order(0)
@Component
public class OrderTransaction implements Ordered {

    @Pointcut("@annotation(framework.spring.annotation.Log)")
    public void log(){}

    @Before("log()")
    public void before() {
        System.out.println("事务: ...");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
