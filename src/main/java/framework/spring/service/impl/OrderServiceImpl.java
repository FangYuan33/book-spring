package framework.spring.service.impl;

import framework.spring.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    @Override
    public void createOrder() {
        System.out.println("创创创建订单");
    }

    @Override
    public String getOrderById(String id) {
        System.out.println("查查查询订单");
        return id;
    }
}
