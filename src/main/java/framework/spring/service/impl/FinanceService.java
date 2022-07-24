package framework.spring.service.impl;

import framework.spring.annotation.Log;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

@Component
public class FinanceService {
    @Log
    public void addMoney(double money) {
        System.out.println("FinanceService 收钱 === " + money);
    }

    @Log
    public double subtractMoney(double money) {
        // 获取到当前代理对象执行方法
        ((FinanceService) AopContext.currentProxy()).getMoneyById("");

        System.out.println("FinanceService 付钱 === " + money);
        return money;
    }

    @Log
    public double getMoneyById(String id) {
        System.out.println("FinanceService 查询账户，id为" + id);
        return Math.random();
    }
}
