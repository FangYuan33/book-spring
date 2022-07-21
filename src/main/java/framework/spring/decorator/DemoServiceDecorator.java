package framework.spring.decorator;

import framework.spring.service.DemoService;
import framework.spring.utils.LogUtils;

import java.util.List;

/**
 * DemoService的装饰着
 */
public class DemoServiceDecorator implements DemoService {

    private DemoService target;

    public DemoServiceDecorator(DemoService target) {
        this.target = target;
    }

    @Override
    public List<String> findAll() {
        return target.findAll();
    }

    @Override
    public int add(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "add", userId, points);
        return target.add(userId, points);
    }

    @Override
    public int subtract(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "subtract", userId, points);
        return target.subtract(userId, points);
    }

    @Override
    public int multiply(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "multiply", userId, points);
        return target.multiply(userId, points);
    }

    @Override
    public int divide(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "divide", userId, points);
        return target.divide(userId, points);
    }
}
