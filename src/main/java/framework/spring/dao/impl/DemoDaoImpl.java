package framework.spring.dao.impl;

import framework.spring.dao.DemoDao;

import java.util.Arrays;
import java.util.List;

public class DemoDaoImpl implements DemoDao {
    @Override
    public List<String> findAll() {
        return Arrays.asList("你好哇，李银河", "2022-7-1 17:11:39", "太原没有森林");
    }
}
