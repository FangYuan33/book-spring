package framework.spring.factory;

import framework.spring.proxy.IndividualPartner;
import framework.spring.proxy.Partner;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
        IndividualPartner partner = (IndividualPartner) PARTNERS.remove(0);

        return (Partner) Proxy.newProxyInstance(partner.getClass().getClassLoader(), partner.getClass().getInterfaces(),
                new InvocationHandler() {
                    private final int budget = money;
                    private boolean status = false;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk动态代理...");

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

    public static Partner getCglibPartner(int money) {
        Partner partner = PARTNERS.remove(0);

        return (Partner) Enhancer.create(partner.getClass(), new MethodInterceptor() {
            private final int budget = money;
            private boolean status = false;

            @Override
            public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("Cglib动态代理...");

                if ("receiveMoney".equals(method.getName())) {
                    int money = (int) objects[0];

                    objects[0] = money / 2;

                    this.status = money >= budget;
                }

                if (status) {
                    return method.invoke(partner, objects);
                }

                return null;
            }
        });
    }
}
