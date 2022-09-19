package mvc.config;

import mvc.interceptor.DemoInterceptor;
import mvc.interceptor.DemoInterceptor1;
import mvc.interceptor.DemoInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 子容器配置
 * <p>
 * Spring的配置，配置视图解析器
 */
@EnableWebMvc
@Configuration
@ComponentScan(value = "mvc",
        includeFilters = {@ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)})
public class EnableWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private DemoInterceptor demoInterceptor;
    @Autowired
    private DemoInterceptor1 demoInterceptor1;
    @Autowired
    private DemoInterceptor2 demoInterceptor2;

    /**
     * 注册视图解析器，相比于@Bean那种便捷了一些
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
        registry.addInterceptor(demoInterceptor1).addPathPatterns("/**");
        registry.addInterceptor(demoInterceptor2).addPathPatterns("/**");
    }
}
