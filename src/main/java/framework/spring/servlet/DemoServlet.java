package framework.spring.servlet;

import framework.spring.decorator.DemoServiceDecorator;
import framework.spring.factory.BeanFactory;
import framework.spring.service.DemoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/demo")
public class DemoServlet extends HttpServlet {

    private DemoService decoratorDemoService = new DemoServiceDecorator((DemoService) BeanFactory.getBean("demoService"));

    private DemoService abstractMethodDemoService = (DemoService) BeanFactory.getBean("demoService");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(abstractMethodDemoService.findAll().toString());

        abstractMethodDemoService.add("FYuan", 33);
    }
}
