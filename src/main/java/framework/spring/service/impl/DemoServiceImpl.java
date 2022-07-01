package framework.spring.service.impl;

import framework.spring.dao.DemoDao;
import framework.spring.dao.impl.DemoDaoImpl;
import framework.spring.service.DemoService;

import java.util.List;

public class DemoServiceImpl implements DemoService {

    private DemoDao demoDao = new DemoDaoImpl();

    @Override
    public List<String> findAll() {
        return demoDao.findAll();
    }
}
