package framework.spring.service.impl;

import framework.spring.dao.DemoDao;
import framework.spring.factory.BeanFactory;
import framework.spring.service.DemoService;
import framework.spring.utils.LogUtils;

import java.util.List;

public class DemoServiceImpl implements DemoService {

    private DemoDao demoDao = (DemoDao) BeanFactory.getBean("demoDao");

    @Override
    public List<String> findAll() {
        return demoDao.findAll();
    }

    @Override
    public int add(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "add", userId, points);
        return points;
    }

    @Override
    public int subtract(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "subtract", userId, points);
        return points;
    }

    @Override
    public int multiply(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "multiply", userId, points);
        return points;
    }

    @Override
    public int divide(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "divide", userId, points);
        return points;
    }
}
