package framework.spring.service.impl;

import framework.spring.annotation.Log;
import org.springframework.stereotype.Component;

@Component
public class FinanceService {
    @Log
    public void addMoney(double money) {
        System.out.println("FinanceService 收钱 === " + money);
    }

    public double subtractMoney(double money) {
        System.out.println("FinanceService 付钱 === " + money);
        return money;
    }

    public double getMoneyById(String id) {
        System.out.println("FinanceService 查询账户，id为" + id);
        return Math.random();
    }
}
