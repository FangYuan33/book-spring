package framework.spring;

import framework.spring.config.AspectJAOPConfiguration;
import framework.spring.factory.PartnerPlatform;
import framework.spring.pojo.Player;
import framework.spring.proxy.Logger;
import framework.spring.proxy.Partner;
import framework.spring.service.OrderService;
import framework.spring.service.impl.FinanceService;
import framework.spring.service.impl.OrderServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPEasyApplication {

    public static void main(String[] args) {
        annotationProxy();
    }

    private static void annotationProxy() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAOPConfiguration.class, Logger.class,OrderServiceImpl.class, FinanceService.class);
        context.refresh();

        OrderService orderService = context.getBean(OrderService.class);
        orderService.getOrderById("FY");
        orderService.createOrder();

        FinanceService financeService = context.getBean(FinanceService.class);
        financeService.addMoney(33);
        financeService.getMoneyById("FY");
        financeService.subtractMoney(11);
    }

    private static void xmlProxy() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xmlaspect.xml");
        FinanceService financeService = context.getBean(FinanceService.class);

        financeService.addMoney(33);
        financeService.subtractMoney(11);

        System.out.println(financeService.getMoneyById("FYuan"));
    }

    private static void jdkCglib() {
        Player player = new Player("郝吾撩");

        Partner partner = PartnerPlatform.getPartner(100);

        partner.receiveMoney(0);
        partner.playWith(player);

        Partner cglibPartner = PartnerPlatform.getCglibPartner(100);
        cglibPartner.receiveMoney(100);
        cglibPartner.playWith(player);
    }
}
