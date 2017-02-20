package cjcx.dao;

import cjcx.entity.Cj;
import cjcx.entity.Grade;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */
@Repository("cjDao")
public class CjDaoImpl extends BaseDao implements CjDao {

    @Resource(name = "JdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int addCj(Cj cj) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        return (Integer) sessionFactory.getCurrentSession().save(cj);
        return (Integer) this.getHibernateTemplate().save(cj);
    }

    public void addCjs(List<Cj> cjs) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        for(Cj cj:cjs){
            for(Grade g:cj.getGrades()){
                g.setCj(cj);
            }
            hibernateTemplate.save(cj);
        }
    }

    public Cj getCjById(int id) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        return sessionFactory.getCurrentSession().get(Cj.class, id);
       return this.getHibernateTemplate().get(Cj.class,id);
    }

    public List<Cj> getCjbyStudentName(String name) {
        return null;
    }

    public List<Cj> getCjbyIdNumber(String idnumber) {
        return null;
    }

    public List<Cj> getCjs(final int page,final int rows,final String stuname,final String idNumber,final String examNumber) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        final StringBuilder sql = new StringBuilder("from Cj where exam.number=:examNumber and ");
        final HashMap<String,String> params = new HashMap<String, String>();
        boolean isStuNameEmpty = StringUtils.isEmpty(stuname);
        if(!isStuNameEmpty){
            sql.append("studentName=:studentName and ");
            params.put("studentName",stuname);
        }
        boolean isIdNumberEmpty = StringUtils.isEmpty(idNumber);
        if(!isIdNumberEmpty){
            sql.append("idNumber=:idNumber and ");
            params.put("idNumber",idNumber);
        }
        sql.delete(sql.length()-5,sql.length());
        List cjs = hibernateTemplate.execute(new HibernateCallback<List>() {
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(sql.toString());
                query.setParameter("examNumber", examNumber);
                for (String name : params.keySet()) {
                    query.setParameter(name, params.get(name));
                }
                query.setFirstResult((page - 1) * rows);
                query.setMaxResults(rows);
                return query.list();
            }
        });
        return cjs;
//        Query query = session.createQuery(sql.toString());
//        query.setParameter("examNumber",examNumber);
//        if(!isStuNameEmpty){
//            query.setParameter("studentName",stuname);
//        }
//        if(!isIdNumberEmpty){
//            query.setParameter("idNumber",idNumber);
//        }
//        query.setFirstResult((page-1)*rows);
//        query.setMaxResults(rows);
//        List<Cj> list = query.list();
    }

    public List<Cj> getCjs(String studentName, String idNumber, String examNumber) {
        StringBuilder sql = new StringBuilder("from Cj where exam.number=:examNumber and ");
        List<String> paramsName = new ArrayList<String>();
        paramsName.add("examNumber");
        List<String> paramsValue = new ArrayList<String>();
        paramsValue.add(examNumber);
        if(!StringUtils.isEmpty(studentName)){
            sql.append("studentName=:studentName and ");
            paramsName.add("studentName");
            paramsValue.add(studentName);
        }
        if(!StringUtils.isEmpty(idNumber)){
            sql.append("idNumber=:idNumber and ");
            paramsName.add("idNumber");
            paramsValue.add(idNumber);
        }
        sql.delete(sql.length()-5,sql.length());
        return (List<Cj>) this.getHibernateTemplate().findByNamedParam(sql.toString(),paramsName.toArray(new String[paramsName.size()]),paramsValue.toArray());
    }

    public void updateCj(Cj cj) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        session.saveOrUpdate(cj);
//        session.flush();
        this.getHibernateTemplate().update(cj);
    }

    public void deleteCj(Cj cj) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        sessionFactory.getCurrentSession().delete(cj);
        this.getHibernateTemplate().delete(cj);
    }

    public Long showNumberOfCj(String stuname,String idNumber,String examNumber) {
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//        Query query = sessionFactory.getCurrentSession().createQuery("select count(*) from Cj where exam.number = :examNumber");
//        query.setParameter("examNumber",examNumber);
//        return (Long) query.uniqueResult();
        StringBuilder sql = new StringBuilder("select count(*) from Cj where exam.number=:examNumber and ");
        LinkedList<String> paramsName = new LinkedList();
        paramsName.add("examNumber");
        LinkedList paramsValue = new LinkedList();
        paramsValue.add(examNumber);
        if (!StringUtils.isEmpty(stuname)){
            sql.append("studentName=:studentName and ");
            paramsName.add("studentName");
            paramsValue.add(stuname);
        }
        if(!StringUtils.isEmpty(idNumber)){
            sql.append("idNumber=:idNumber and ");
            paramsName.add("idNumber");
            paramsValue.add(idNumber);
        }
        sql.delete(sql.length()-5,sql.length());
        return (Long) this.getHibernateTemplate().findByNamedParam(sql.toString(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray()).iterator().next();
    }

}
