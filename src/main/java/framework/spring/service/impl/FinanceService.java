package framework.spring.service.impl;

import framework.spring.annotation.Log;
import framework.spring.annotation.Transactional;
import framework.spring.dao.impl.FinanceDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class FinanceService {

    @Autowired
    private FinanceDao financeDao;

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

    @Transactional
    public void transfer(Long source, Long target, int money) throws SQLException {
        financeDao.subtractMoney(source, money);
//        System.out.println(1/0);
        financeDao.addMoney(target, money);
        System.out.println(source + "转账给" + target + " " + money + "元");
    }
}
