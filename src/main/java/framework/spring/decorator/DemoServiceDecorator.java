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
        LogUtils.printLog(this.getClass().getName(), "findAll");
        return target.findAll();
    }

    @Override
    public int add(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "add", userId, points);
        return target.add(userId, points);
    }
}
