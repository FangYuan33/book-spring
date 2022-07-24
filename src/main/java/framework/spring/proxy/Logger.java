package framework.spring.proxy;

import framework.spring.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Logger {

    /**
     * 用空方法指定一个注解为切入点
     */
    @Pointcut("@annotation(framework.spring.annotation.Log)")
    public void logAnnotation(){}

    /**
     * 用 @Pointcut注解标注在空方法上，能够复用
     */
    @Pointcut("execution(* framework.spring.service.*.*(String))")
    public void defaultPointcut(){}

    @Before("execution(public * framework.spring.service.impl.FinanceService.*(..))")
    public void beforePrint() {
        System.out.println("Logger beforePrint run ......");
    }

    @After("execution(* framework.spring.service.*.*(String))")
    public void afterPrint() {
        System.out.println("Logger afterPrint run ......");
    }

    @AfterReturning(value = "defaultPointcut()", returning = "returnValue")
    public void afterReturningPrint(Object returnValue) {
        System.out.println("Logger afterReturningPrint run ......");
        System.out.println("返回的数据: " + returnValue);
    }

    @AfterThrowing(value = "defaultPointcut()", throwing = "e")
    public void afterThrowingPrint(Exception e) {
        System.out.println("Logger afterThrowingPrint run ......");
        System.out.println("抛出的异常: " + e.getMessage());
    }

    @Around("logAnnotation()")
//    @Around("execution(public * framework.spring.service.impl.FinanceService.addMoney(..))")
    public Object aroundPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logger aroundPrint before run ......");
        try {
            Object retVal = joinPoint.proceed();

            System.out.println("被拦截的类: " + joinPoint.getTarget());
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            System.out.println("被拦截的方法: " + methodSignature.getName());
            Log log = methodSignature.getMethod().getAnnotation(Log.class);
            System.out.println("被拦截的方法的注解: " + log);
            System.out.println("被拦截的方法的入参: " + Arrays.toString(joinPoint.getArgs()));

            System.out.println("Logger aroundPrint afterReturning run ......");
            return retVal;
        } catch (Throwable e) {
            System.out.println("Logger aroundPrint afterThrowing run ......");
            throw e;
        } finally {
            System.out.println("Logger aroundPrint after run ......");
        }
    }
}
