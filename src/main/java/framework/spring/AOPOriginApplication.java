package framework.spring;

import framework.spring.config.AspectJAOPConfiguration;
import framework.spring.dao.impl.FinanceDao;
import framework.spring.proxy.Logger;
import framework.spring.service.impl.FinanceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPOriginApplication {
    public static void main(String[] args) {
        annotationAwareAspectJAutoProxyCreator();
    }

    private static void annotationAwareAspectJAutoProxyCreator() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAOPConfiguration.class, FinanceService.class, FinanceDao.class, Logger.class);
        context.refresh();
    }
}
