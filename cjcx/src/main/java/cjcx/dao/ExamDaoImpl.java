package cjcx.dao;

import cjcx.entity.Exam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
@Repository("examDao")
public class ExamDaoImpl extends BaseDao implements ExamDao {

//    @Resource(name = "sessionFactory")
//    private SessionFactory sessionFactory;

    public void addExam(Exam exam) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        sessionFactory.getCurrentSession().save(exam);
        this.getHibernateTemplate().save(exam);
    }

    public void deleteExam(String number) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(session.get(Exam.class,number));
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        Exam exam = hibernateTemplate.get(Exam.class, number);
        hibernateTemplate.delete(exam);
    }

    public void deleteExam(Exam exam) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        sessionFactory.getCurrentSession().delete(exam);
        this.getHibernateTemplate().delete(exam);
    }

    public void updateExam(Exam exam) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        sessionFactory.getCurrentSession().update(exam);
        this.getHibernateTemplate().update(exam);
    }

    public Exam getExambyNumber(String number) {
        return this.getHibernateTemplate().get(Exam.class, number);
    }

    public List<Exam> getExams() {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
         return (List<Exam>) this.getHibernateTemplate().find("from Exam ");
//        return sessionFactory.getCurrentSession().createQuery("from Exam ").list();
    }
}
