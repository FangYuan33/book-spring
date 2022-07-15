package framework.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;

/**
 * 不指定泛型的话，什么泛型的PayloadEvent都会被监听到
 */
public class PayloadObjectApplicationListener implements ApplicationListener<PayloadApplicationEvent<?>> {
    @Override
    public void onApplicationEvent(PayloadApplicationEvent event) {
        System.out.println("监听到PayloadApplicationEvent: " + event.getPayload());
    }
}
