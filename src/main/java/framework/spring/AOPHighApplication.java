package framework.spring;

import framework.spring.config.AspectJAOPConfiguration;
import framework.spring.dao.impl.FinanceDao;
import framework.spring.proxy.IntroductionAspect;
import framework.spring.proxy.OrderTransaction;
import framework.spring.service.impl.FinanceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPHighApplication {
    public static void main(String[] args) {
        introduction();
    }

    private static void introduction() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAOPConfiguration.class, FinanceService.class, FinanceDao.class, OrderTransaction.class
                , IntroductionAspect.class);
        context.refresh();

        FinanceService financeService = context.getBean(FinanceService.class);
        try {
            financeService.transfer(2L, 1L, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
