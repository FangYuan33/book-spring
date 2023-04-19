package framework.spring.lifecycle;

import org.springframework.context.SmartLifecycle;

/**
 * 生命周期
 *
 * @author FangYuan
 * @since 2023-04-19 15:28:49
 */
public class MySmartLifecycle implements SmartLifecycle {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
