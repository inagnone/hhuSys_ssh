package cjcx.dao;

import cjcx.entity.Exam;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public interface ExamDao {
    public void addExam(Exam exam);
    public void deleteExam(String number);
    public void deleteExam(Exam exam);
    public void updateExam(Exam exam);
    public Exam getExambyNumber(String number);
    public List<Exam> getExams();
}
