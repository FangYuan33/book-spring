package framework.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 用来测试事件是否能在 多个层级 的容器中广播
 */
public class HierarchicalEvent extends ApplicationEvent {

    public HierarchicalEvent(Object source) {
        super(source);
    }
}
