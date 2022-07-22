package framework.spring.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

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

    @AfterReturning("defaultPointcut()")
    public void afterReturningPrint() {
        System.out.println("Logger afterReturningPrint run ......");
    }

    @AfterThrowing("defaultPointcut()")
    public void afterThrowingPrint() {
        System.out.println("Logger afterThrowingPrint run ......");
    }

    @Around("logAnnotation()")
//    @Around("execution(public * framework.spring.service.impl.FinanceService.addMoney(..))")
    public Object aroundPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logger aroundPrint before run ......");
        try {
            Object retVal = joinPoint.proceed();
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
