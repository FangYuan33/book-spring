package mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DemoInterceptor1 implements HandlerInterceptor {

    /**
     * 在执行 Controller 的方法之前触发，可用于编码、权限校验拦截等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("DemoInterceptor preHandle1 ......");
        return true;
    }

    /**
     * 在执行完 Controller 方法后，跳转页面 / 返回 json 数据之前触发
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("DemoInterceptor postHandle1 ......");
    }

    /**
     * 在完全执行完 Controller 方法后触发，可用于异常处理、性能监控等
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("DemoInterceptor afterCompletion1 ......");
    }
}
