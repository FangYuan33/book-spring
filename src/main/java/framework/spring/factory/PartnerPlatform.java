package framework.spring.factory;

import framework.spring.proxy.IndividualPartner;
import framework.spring.proxy.Partner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class PartnerPlatform {

    private static List<Partner> PARTNERS;

    static {
        PARTNERS = new ArrayList<>();
        PARTNERS.add(new IndividualPartner("肖洁洁"));
        PARTNERS.add(new IndividualPartner("戴葛嗝"));
    }

    /**
     * 获取陪玩
     *
     * @param money 指定陪玩的预算，供之后调用 receiveMoney 方法时判断支付的够不够
     */
    public static Partner getPartner(int money) {
        Partner partner = PARTNERS.remove(0);

        return (Partner) Proxy.newProxyInstance(partner.getClass().getClassLoader(), partner.getClass().getInterfaces(),
                new InvocationHandler() {
                    private int budget = money;
                    private boolean status = false;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("receiveMoney".equals(method.getName())) {
                            // 支付的钱
                            int money = (int) args[0];

                            // 平台抽成
                            args[0] = money / 2;

                            this.status = money >= budget;
                        }

                        // 控制方法的执行
                        if (status) {
                            return method.invoke(partner, args);
                        } else {
                            return null;
                        }
                    }
                });
    }
}
