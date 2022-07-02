package framework.spring.service.impl;

import framework.spring.dao.DemoDao;
import framework.spring.service.DemoService;
import lombok.Setter;

import java.util.List;

public class DemoServiceImpl implements DemoService {

    @Setter
    private DemoDao demoDao;

    @Override
    public List<String> findAll() {
        return demoDao.findAll();
    }
}
