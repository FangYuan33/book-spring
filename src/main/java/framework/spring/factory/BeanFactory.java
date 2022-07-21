package framework.spring.factory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
                            Class<?> proxyAdvisorClass = Class.forName(proxyAdvisorClassName);
                            String[] methods = BEAN_CONFIG.getProperty(beanName + ".proxy.methods").split(",");

                            InvocationHandler proxyHandler = (InvocationHandler) proxyAdvisorClass
                                    .getConstructors()[0].newInstance(bean, Arrays.asList(methods));

                            // 动态代理类
                            Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                                    bean.getClass().getInterfaces(),
                                    proxyHandler);

                            bean = proxy;
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
