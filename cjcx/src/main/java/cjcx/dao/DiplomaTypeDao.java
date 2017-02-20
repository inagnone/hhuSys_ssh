package cjcx.dao;

import cjcx.entity.DiplomaType;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
public interface DiplomaTypeDao {
    public void addDiplomaType(DiplomaType diplomaType);
    public void deleteDiplomaType(String number);
    public void deleteDiplomaType(DiplomaType diplomaType);
    public void updateDiplomaType(DiplomaType diplomaType);
    public DiplomaType getDiplomaTypebyNumber(String number);
    public List<DiplomaType> getDiplomaTypes();
}
