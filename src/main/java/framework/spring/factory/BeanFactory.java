package framework.spring.factory;

import java.io.IOException;
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
                        beanCache.put(beanName, bean);

                        return bean;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("BeanFactory have not [" + beanName + "] bean!", e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("[" + beanName + "] instantiation error!", e);
        }

        return beanCache.get(beanName);
    }
}
