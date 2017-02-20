package cjcx.service;

import cjcx.entity.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
public interface TestService {
    public List<Test> getTests();

    public List<Test> getTestsOfExam(String number);

    public Test getTestById(int id);

    public int addTest(Test test);

    public void updateTest(Test test);
}
