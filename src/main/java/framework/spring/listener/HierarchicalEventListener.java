package framework.spring.listener;

import framework.spring.event.HierarchicalEvent;
import org.springframework.context.ApplicationListener;

public class HierarchicalEventListener implements ApplicationListener<HierarchicalEvent> {
    @Override
    public void onApplicationEvent(HierarchicalEvent event) {
        System.out.println("监听到HierarchicalEvent: " + event.toString());
    }
}
