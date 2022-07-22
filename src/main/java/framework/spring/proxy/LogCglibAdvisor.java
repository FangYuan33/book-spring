package framework.spring.proxy;

import framework.spring.utils.LogUtils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class LogCglibAdvisor implements MethodInterceptor {

    private Object target;

    private List<String> methods;

    public LogCglibAdvisor(Object target, List<String> methods) {
        this.target = target;
        this.methods = methods;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (this.methods.contains(method.getName())) {
            LogUtils.printLog(target.getClass().getName(), method.getName(), objects);
        }

        return method.invoke(target, objects);
    }
}
