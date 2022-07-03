package framework.spring.pojo;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Data
@Component
public class Cat implements InitializingBean, DisposableBean {

    @Value("${name}")
    private String name;

    private Person master;

    /**
     * value注入多个Bean，用SpEL两个大括号接收
     */
    @Value("#{{red, me, javaBeanConfig}}")
    private List<Object> beans;

    /**
     * 方法访问权限无限制要求（SpringFramework 底层会反射调用的）
     * 方法无参数（如果真的设置了参数，SpringFramework 也不知道传什么进去）
     * 方法无返回值（返回给 SpringFramework 也没有意义）
     * 可以抛出异常（异常不由自己处理，交予 SpringFramework 可以打断 Bean 的初始化 / 销毁步骤）
     */
    private void init() {
        System.out.println(name + "出生了");
    }

    private void destroy1() {
        System.out.println(name + "说了再见");
    }

    /**
     * 使用JSR250规范也是可以的，而且JSR250的执行优先级要高于init-method
     */
    @PostConstruct
    private void init2() {
        System.out.println(name + "出生了（JSR250）");
    }

    @PreDestroy
    private void destroy2() {
        System.out.println(name + "说了再见（JSR250）");
    }

    /**
     * 下面是实现接口来定义干预生命周期的两种方法
     *
     * 这个方法的名字就很直白，说明了是在属性赋值之后执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name + "出生了（InitializingBean）");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(name + "说了再见（DisposableBean）");
    }
}
