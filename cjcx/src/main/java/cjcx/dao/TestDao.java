package cjcx.dao;

import cjcx.entity.Exam;
import cjcx.entity.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
public interface TestDao {

    public List<Test> getTests();

    public List<Test> getTestsOfExam(Exam exam);

    public List<Test> getTestById(String number);

    public int addTest(Test test);

    public void updateTest(Test test);
}
