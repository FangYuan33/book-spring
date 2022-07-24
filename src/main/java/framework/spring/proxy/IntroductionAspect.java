package framework.spring.proxy;

import framework.spring.service.MoneyValidator;
import framework.spring.service.impl.MoneyValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 引介通知直接在类上增加新的方法和属性
 */
@Aspect
@Component
public class IntroductionAspect {

    /**
     * 对Fiance使用引介通知
     */
    @DeclareParents(value = "framework.spring.service.impl.FinanceService",
            defaultImpl = MoneyValidatorImpl.class)
    private MoneyValidator moneyValidator;

    @Before("execution(* framework.spring.service.impl.FinanceService.transfer(..))")
    public void beforeTransfer(JoinPoint joinPoint) {
        int money = (int) joinPoint.getArgs()[2];

        MoneyValidator moneyValidator = (MoneyValidator) joinPoint.getThis();
        if (moneyValidator.validate(money)) {
            System.out.println("转账金额校验通过");
        } else {
            throw new IllegalArgumentException("转账金额异常...");
        }
    }
}
