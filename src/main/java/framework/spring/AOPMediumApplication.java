package framework.spring;

import framework.spring.config.AspectJAOPConfiguration;
import framework.spring.dao.impl.FinanceDao;
import framework.spring.proxy.Logger;
import framework.spring.proxy.OrderTransaction;
import framework.spring.service.impl.FinanceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPMediumApplication {
    public static void main(String[] args) {
        transaction();
    }

    private static void transaction() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAOPConfiguration.class, FinanceService.class, FinanceDao.class, OrderTransaction.class);
        context.refresh();

        FinanceService financeService = context.getBean(FinanceService.class);
        try {
            financeService.transfer(2L, 1L, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void methodSignature() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAOPConfiguration.class, Logger.class, FinanceService.class, OrderTransaction.class);
        context.refresh();

        FinanceService financeService = context.getBean(FinanceService.class);
//        financeService.addMoney(999);
        financeService.subtractMoney(33);
    }
}
