package framework.spring.postprocessor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * 实现DIY的AOP织入
 */
public class AopProxyPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private final Map<PointcutExpression, Method> beforePointcutMethodMap = new ConcurrentReferenceHashMap<>();

    private ConfigurableListableBeanFactory beanFactory;

    /**
     * 解析出来所有的切面中的before通知并保存起来
     */
    @PostConstruct
    public void initAspectAndPointcuts() {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);

            String beanClassName = beanDefinition.getBeanClassName();
            if (!StringUtils.hasText(beanClassName)) {
                continue;
            }
            // 检查class上是否标注了Aspect注解
            Class<?> clazz = ClassUtils.resolveClassName(beanClassName, ClassUtils.getDefaultClassLoader());
            if (!clazz.isAnnotationPresent(Aspect.class)) {
                continue;
            }

            // 切入点表达式解析器
            PointcutParser pointcutParser =
                    PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();
            // 解析方法
            ReflectionUtils.doWithMethods(clazz, method -> {
                // 切入点表达式
                Before before = method.getAnnotation(Before.class);

                if (before != null) {
                    String pointcutExp = before.value();
                    PointcutExpression pointcutExpression = pointcutParser.parsePointcutExpression(pointcutExp);

                    // 放入缓存区保存起来
                    beforePointcutMethodMap.put(pointcutExpression, method);
                }
            }, method -> {
                // 过滤出before通知的方法
                return method.isAnnotationPresent(Before.class);
            });
        }
    }

    /**
     * 构造代理对象
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Aspect.class)) {
            return bean;
        }

        // 检查当前类有没有被切入
        ArrayList<Method> proxyMethods = new ArrayList<>();
        beforePointcutMethodMap.forEach(((pointcutExpression, method) -> {
            if (pointcutExpression.couldMatchJoinPointsInType(bean.getClass())) {
                proxyMethods.add(method);
            }
        }));

        if (proxyMethods.isEmpty()) {
            return bean;
        }

        return Enhancer.create(bean.getClass(), (MethodInterceptor) (proxy, method, args, methodProxy) -> {
            for (Method proxyMethod : proxyMethods) {
                Object aspectBean = beanFactory.getBean(proxyMethod.getDeclaringClass());

                // 执行前置通知的方法
                proxyMethod.invoke(aspectBean);
            }

            // 执行原方法
            return methodProxy.invokeSuper(proxy, args);
        });
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
}
