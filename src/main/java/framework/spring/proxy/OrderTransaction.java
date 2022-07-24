package framework.spring.proxy;

import framework.spring.utils.JdbcUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * order注解 或 实现 ordered接口来定义切面的执行顺序
 */
@Aspect
//@Order(0)
@Component
public class OrderTransaction implements Ordered {

    @Pointcut("@annotation(framework.spring.annotation.Log)")
    public void log(){}

    @Pointcut("@annotation(framework.spring.annotation.Transactional)")
    public void transactional(){}

    @Before("log()")
    public void before() {
        System.out.println("事务: ...");
    }


    @Around("transactional()")
    public Object doWithTransaction(ProceedingJoinPoint proceedingJoinPoint) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        try {
            // 开启事务
            connection.setAutoCommit(false);
            // 执行方法
            Object result = proceedingJoinPoint.proceed();
            // 执行成功，提交事务
            connection.commit();

            return result;
        } catch (Throwable e) {
            // 执行失败，回滚事务
            connection.rollback();

            throw new RuntimeException(e);
        } finally {
            JdbcUtils.remove();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
