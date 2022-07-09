package framework.spring.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 注解式实现事件监听机制，监听容器关闭事件
 */
@Component
public class ContextClosedApplicationListener {

    @EventListener
    public void onContextClosedEvent(ContextClosedEvent event) {
        System.out.println("监听到容器关闭事件..." + event.toString());
    }
}
