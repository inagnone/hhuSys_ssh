package cjcx.dao;

import cjcx.entity.Exam;
import cjcx.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
@Repository("testDao")
public class TestDaoImpl extends BaseDao implements TestDao {

//    @Resource(name = "sessionFactory")
//    private SessionFactory sessionFactory;

    public List<Test> getTests() {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        List list = session.createQuery("from Test ").list();
        List<Test> list = (List<Test>) this.getHibernateTemplate().find("from Test ");
        return list;
    }

    public List<Test> getTestsOfExam(Exam exam) {
        return null;
    }

    public List<Test> getTestById(String number) {
        return null;
    }

    public int addTest(Test test) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        int id = (Integer) session.save(test);
        int id = (Integer)this.getHibernateTemplate().save(test);
        return id;
    }

    public void updateTest(Test test) {

    }
}
