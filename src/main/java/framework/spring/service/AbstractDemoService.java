package framework.spring.service;

import framework.spring.utils.LogUtils;

import java.util.List;

/**
 * 模板方法模式增强添加日志
 */
public abstract class AbstractDemoService implements DemoService {

    @Override
    public List<String> findAll() {
        LogUtils.printLog(this.getClass().getName(), "findAll");
        return doFindAll();
    }

    protected abstract List<String> doFindAll();

    @Override
    public int add(String userId, int points) {
        LogUtils.printLog(this.getClass().getName(), "add", userId, points);
        return doAdd(userId, points);
    }

    protected abstract int doAdd(String userId, int points);
}
