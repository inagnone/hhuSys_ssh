package cjcx.dao;

import cjcx.entity.DiplomaType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
@Repository("diplomaTypeDao")
public class DiplomaTypeDaoImpl extends BaseDao implements DiplomaTypeDao {
    public void addDiplomaType(DiplomaType diplomaType) {

    }

    public void deleteDiplomaType(String number) {

    }

    public void deleteDiplomaType(DiplomaType diplomaType) {

    }

    public void updateDiplomaType(DiplomaType diplomaType) {

    }

    public DiplomaType getDiplomaTypebyNumber(String number) {
        return (DiplomaType) this.getHibernateTemplate().findByNamedParam("from DiplomaType where id =:id","id",number).iterator().next();
    }

    public List<DiplomaType> getDiplomaTypes() {
        return (List<DiplomaType>) this.getHibernateTemplate().find("from DiplomaType");
    }
}
