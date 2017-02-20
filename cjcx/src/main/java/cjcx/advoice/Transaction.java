package cjcx.advoice;

import org.aspectj.lang.annotation.After;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/12/22.
 */
public class Transaction {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * 事务切入点:service包下所有类的所有方法
     */
    private void excute(){};

    /**
     * 开启事务
     */
    public void beginTransaction(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }

    /**
     * 提交事务
     */
    @After("excute()")
    public void commitTransaction(){
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().commit();
    }

    /**
     * 关闭事务
     */
    public void closeTransaction(){
        Session session = sessionFactory.getCurrentSession();
        if(session.isOpen()){
            session.close();
        }
    }
}
