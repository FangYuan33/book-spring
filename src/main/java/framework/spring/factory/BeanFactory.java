package framework.spring.factory;

import framework.spring.utils.LogUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Bean的静态工厂
 */
public class BeanFactory {

    private static final Map<String, Object> beanCache = new HashMap<>();

    private static final Properties BEAN_CONFIG;

    static {
        BEAN_CONFIG = new Properties();

        try {
            BEAN_CONFIG.load(BeanFactory.class.getClassLoader().getResourceAsStream("factory.properties"));
        } catch (IOException ioException) {
            throw new RuntimeException("BeanFactory initialize error: " + ioException.getMessage());
        }
    }

    public static Object getBean(String beanName) {
        try {
            if (!beanCache.containsKey(beanName)) {
                synchronized (BeanFactory.class) {
                    if (!beanCache.containsKey(beanName)) {
                        Object bean = Class.forName(BEAN_CONFIG.getProperty(beanName)).newInstance();

                        String proxyAdvisorClassName = BEAN_CONFIG.getProperty(beanName + ".proxy.class");
                        if (proxyAdvisorClassName != null && proxyAdvisorClassName.trim().length() > 0) {
                            List<String> methods =
                                    Arrays.asList(BEAN_CONFIG.getProperty(beanName + ".proxy.methods").split(","));

                            // 动态代理类
                            if (proxyAdvisorClassName.contains("LogAdvisor")) {
                                Class<?> proxyAdvisorClass = Class.forName(proxyAdvisorClassName);
                                Object proxy = proxyAdvisorClass.getConstructors()[0].newInstance(bean, methods);

                                bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                                        bean.getClass().getInterfaces(),
                                        (InvocationHandler) proxy);
                            } else {
                                Object finalBean = bean;
                                bean = Enhancer.create(bean.getClass(),
                                        (MethodInterceptor) (o, method, objects, methodProxy) -> {
                                    if (methods.contains(method.getName())) {
                                        LogUtils.printLog(finalBean.getClass().getName(), method.getName(), objects);
                                    }

                                    return method.invoke(finalBean, objects);
                                });
                            }

                        }

                        beanCache.put(beanName, bean);

                        return bean;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("BeanFactory have not [" + beanName + "] bean!", e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("[" + beanName + "] instantiation error!", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return beanCache.get(beanName);
    }
}
