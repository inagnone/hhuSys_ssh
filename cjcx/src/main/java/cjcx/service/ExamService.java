package cjcx.service;

import cjcx.entity.Exam;
import cjcx.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
public interface ExamService {

    public void addExam(User user, Exam exam);

    public void updateExam(User user,Exam exam);

    public void deleteExam(User user,Exam exam);

    public void deleteExams(User user,String[] numbers);

    public List<Exam> listExam();

    public Exam getExamByNumber(String Number);

    public List<Exam> getExamByNumberAndName(String number,String name);
}
