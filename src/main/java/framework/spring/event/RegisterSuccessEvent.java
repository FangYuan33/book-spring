package framework.spring.event;

import org.springframework.context.ApplicationEvent;

public class RegisterSuccessEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RegisterSuccessEvent(Object source) {
        super(source);
    }
}
