package cjcx.dao;

import cjcx.entity.Diploma;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */
@Repository("diplomaDao")
public class DiplomaDaoImpl extends BaseDao implements DiplomaDao {
    public int addDiploma(Diploma diploma) {
        int id = (Integer) this.getHibernateTemplate().save(diploma);
        return  id;
    }

    public void addDiplomas(List<Diploma> diplomas) {
        for(Diploma diploma:diplomas){
            this.getHibernateTemplate().save(diploma);
        }
    }

    public Diploma getDiplomaById(int id) {
        return this.getHibernateTemplate().get(Diploma.class,id);
    }

    public List<Diploma> getDiplomabyStudentName(String name) {
        return null;
    }

    public List<Diploma> getDiplomabyIdNumber(String idnumber) {
        return null;
    }

    public List<Diploma> getDiplomas(final int page,final int rows,final Map<String,String> params) {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        final StringBuilder sql = new StringBuilder("from diploma where");
        for(String name:params.keySet()){
            sql.append(" "+name+"=:"+name+" and ");
        }
        sql.delete(sql.length()-5,sql.length());
        List diplomas = hibernateTemplate.execute(new HibernateCallback<List>() {
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(sql.toString());
                for (String name : params.keySet()) {
                    query.setParameter(name, params.get(name));
                }
                query.setFirstResult((page - 1) * rows);
                query.setMaxResults(rows);
                return query.list();
            }
        });
        return diplomas;
    }

    public List<Diploma> getDiplomas(String studentName, String idNumber, String examNumber) {
        return null;
    }

    public void updateDiploma(Diploma Diploma) {

    }

    public void deleteDiploma(Diploma Diploma) {

    }

    public Long showNumberOfDiploma(Map<String,String> params) {
        StringBuilder sql = new StringBuilder("select count(*) from diploma where");
        for(String name:params.keySet()){
            sql.append(" "+name+"=:"+name+" and ");
        }
        sql.delete(sql.length()-5,sql.length());
        return (Long) this.getHibernateTemplate().findByNamedParam(sql.toString(),params.keySet().toArray(new String[params.keySet().size()]),params.values().toArray()).iterator().next();
    }

    public List<String> getDiplomaJobs(String number){
        List<String> job = (List<String>) this.getHibernateTemplate().findByNamedParam("select d.job from Diploma d where d.diplomaType.number = :number group by d.job","number",number);
        return job;
    }
}
