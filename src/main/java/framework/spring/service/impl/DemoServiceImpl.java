package framework.spring.service.impl;

import framework.spring.dao.DemoDao;
import framework.spring.factory.BeanFactory;
import framework.spring.service.AbstractDemoService;
import framework.spring.service.DemoService;

import java.util.List;

public class DemoServiceImpl extends AbstractDemoService implements DemoService {

    private DemoDao demoDao = (DemoDao) BeanFactory.getBean("demoDao");

    @Override
    public List<String> findAll() {
        return demoDao.findAll();
    }

    @Override
    protected List<String> doFindAll() {
        return demoDao.findAll();
    }

    @Override
    public int add(String userId, int points) {
        return points;
    }

    @Override
    protected int doAdd(String userId, int points) {
        return points;
    }
}
