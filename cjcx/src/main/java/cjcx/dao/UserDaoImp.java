package cjcx.dao;

import cjcx.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */
@Repository("userDao")
class UserDaoImp extends BaseDao implements  UserDao {

    public User getUser(User user) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(User.class, user.getId());
        return this.getHibernateTemplate().get(User.class,user.getId());
    }

    public User getUserbyName(String name){
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("from User where name=:name");
//        query.setParameter("name",name);
//        return (User) query.uniqueResult();
        List<User> user = (List<User>) this.getHibernateTemplate().findByNamedParam("from User where name=:name", "name", name);
        return user.get(0);
    }

    public User getUserById(int id){
        return this.getHibernateTemplate().get(User.class, id);
    }

    public void saveUser(User user){
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
        this.getHibernateTemplate().save(user);
    }

    public void updateUser(User user){
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        session.update(user);
        this.getHibernateTemplate().update(user);
    }
}
