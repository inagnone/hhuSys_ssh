package cjcx.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/15.
 */
public class BaseDao extends HibernateDaoSupport {

    @Resource(name = "sessionFactory")
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
