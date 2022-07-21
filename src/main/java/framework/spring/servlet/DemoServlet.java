package framework.spring.servlet;

import framework.spring.decorator.DemoServiceDecorator;
import framework.spring.factory.BeanFactory;
import framework.spring.service.DemoService;
import framework.spring.utils.LogUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Proxy;

@WebServlet(urlPatterns = "/demo")
public class DemoServlet extends HttpServlet {

    private DemoService demoService = (DemoService) BeanFactory.getBean("demoService");

    private DemoService decoratorDemoService = new DemoServiceDecorator((DemoService) BeanFactory.getBean("demoService"));

    private DemoService abstractMethodDemoService = (DemoService) BeanFactory.getBean("demoService");

    @Override
    public void init() {
        // jdkProxy();
    }

    /**
     * jdk动态代理默认给所有的方法都增强
     */
    private void jdkProxy() {
        DemoService demoService = (DemoService) BeanFactory.getBean("demoService");
        Class<? extends DemoService> demoServiceClass = demoService.getClass();

        // jdk动态代理
        this.demoService = (DemoService) Proxy
                .newProxyInstance(demoServiceClass.getClassLoader(), demoServiceClass.getInterfaces(),
                        ((proxy, method, args) -> {
                            // 条件增强方法
                            if ("add".equals(method.getName())) {
                                LogUtils.printLog(demoServiceClass.getName(), method.getName(), args);
                            }

                            return method.invoke(demoService, args);
                        }));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(demoService.findAll().toString());

        demoService.add("FYuan", 33);
    }
}
