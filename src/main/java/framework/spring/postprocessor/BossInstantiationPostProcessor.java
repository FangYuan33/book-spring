package framework.spring.postprocessor;

import framework.spring.pojo.Boss;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.math.BigDecimal;

public class BossInstantiationPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * 执行时机是在Bean的实例化之前
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("boss".equals(beanName)) {
            Boss boss = new Boss();
            boss.setName("在实例化之前被拦截的Boss");

            return boss;
        }

        // 返回null代表不拦截
        return null;
    }

    /**
     * 为bean赋值
     *
     * 注意下方使用的是 boos2，因为想使用boss 用不了
     * 在postProcessBeforeInstantiation方法执行完成之后，对同一个bean不会再执行该方法，所以使用boss就不能生效了
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("boss2".equals(beanName)) {
            MutablePropertyValues propertyValues = new MutablePropertyValues(pvs);
            propertyValues.addPropertyValue("salary", BigDecimal.valueOf(100000000L));

            return propertyValues;
        }

        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    /**
     * 这个方法可以用来控制 postProcessProperties 的执行，返回false之后，那么 postProcessProperties 方法就不会执行了
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }
}
