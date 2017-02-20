package cjcx.service;

import cjcx.Exception.DeleteExamWithCjException;
import cjcx.annotation.PrivilegeInfo;
import cjcx.dao.CjDao;
import cjcx.dao.ExamDao;
import cjcx.entity.Exam;
import cjcx.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {

    @Resource(name = "examDao")
    private ExamDao examDao;

    @Resource(name = "cjDao")
    private CjDao cjDao;

    @PrivilegeInfo(name = "newExam")
    @CacheEvict(value = "examCache",key = "'list'")
    public void addExam(User user, Exam exam) {
        examDao.addExam(exam);
    }

    @PrivilegeInfo(name = "updateExam")
    @CacheEvict(value = "examCache",key = "'list'")
    public void updateExam(User user,Exam exam) {
        examDao.updateExam(exam);
    }

    @PrivilegeInfo(name = "deleteExam")
    @CacheEvict(value = "examCache",key = "'list'")
    public void deleteExam(User user,Exam exam) {
        Exam examInDB = examDao.getExambyNumber(exam.getNumber());
        boolean isExamNoCj = true;
        isExamNoCj = isExamNoCj(examInDB);
        if(isExamNoCj){
            examDao.deleteExam(exam);
        }else{
            throw new DeleteExamWithCjException("考试删除失败:"+exam.getName()+"存在成绩数据");
        }
    }

    @PrivilegeInfo(name = "deleteExam")
    @CacheEvict(value = "examCache",key = "'list'")
    public void deleteExams(User user,String[] numbers) {
        for(String number:numbers){
            Exam exam = examDao.getExambyNumber(number);
            if(isExamNoCj(exam)){
                examDao.deleteExam(exam);
            }else{
                throw new DeleteExamWithCjException("考试删除失败:"+exam.getName()+"存在成绩数据");
            }
        }
    }

    @Cacheable(value = "examCache",key = "'list'")
    public List<Exam> listExam() {
        List<Exam> exams = examDao.getExams();
        for(Exam exam:exams){
            exam.setListTests();
        }
        return exams;
    }

    public List<Exam> getExamByNumberAndName(String number,String name){
        List<Exam> exams = listExam();
        boolean isNumberEmpty = StringUtils.isEmpty(number);
        boolean isNameEmpty = StringUtils.isEmpty(name);
        if(isNumberEmpty && isNameEmpty){
            return exams;
        }
        List<Exam> result = new ArrayList<Exam>();
        for(Exam e:exams){
            if(!isNumberEmpty && e.getNumber().equals(number)){
                result.add(e);
                continue;
            }
            if(!isNameEmpty && e.getName().equals(name)){
                result.add(e);
            }
        }
        return result;
    }

    public Exam getExamByNumber(String number) {
        List<Exam> exams = listExam();
        for(Exam e:exams){
            if(e.getNumber().equals(number)){
                return e;
            }
        }
        return null;
    }

    /**
     * 检查目标考试是否已经有成绩记录，如果已经有成绩记录，则不允许删除考试
     * @param exam
     * @return
     */
    private boolean isExamNoCj(Exam exam){
        Long amount = cjDao.showNumberOfCj(null, null, exam.getNumber());
        if(amount>0){
            return false;
        }
        return true;
    }
}
