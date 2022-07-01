package framework.spring.service.impl;

import framework.spring.dao.DemoDao;
import framework.spring.factory.BeanFactory;
import framework.spring.service.DemoService;

import java.util.List;

public class DemoServiceImpl implements DemoService {

    private DemoDao demoDao = (DemoDao) BeanFactory.getBean("demoDao");

    @Override
    public List<String> findAll() {
        return demoDao.findAll();
    }
}
