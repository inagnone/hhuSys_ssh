package cjcx.service;

import cjcx.dao.TestDao;
import cjcx.entity.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource(name = "testDao")
    private TestDao testDao;

    public List<Test> getTests() {
        return null;
    }

    public List<Test> getTestsOfExam(String number) {
        return null;
    }

    public Test getTestById(int id) {
        return null;
    }

    public int addTest(Test test) {
        return testDao.addTest(test);
    }

    public void updateTest(Test test) {

    }
}
