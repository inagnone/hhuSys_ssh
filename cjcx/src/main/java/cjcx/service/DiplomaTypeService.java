package cjcx.service;

import cjcx.entity.DiplomaType;
import cjcx.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
public interface DiplomaTypeService {
    public void addDiplomaType(User user, DiplomaType diplomaType);

    public void updateDiplomaType(User user,DiplomaType diplomaType);

    public void deleteDiplomaType(User user,DiplomaType diplomaType);

    public void deleteDiplomaTypes(User user,String[] numbers);

    public List<DiplomaType> listDiplomaType();

    public DiplomaType getDiplomaTypeByNumber(String Number);

    public List<DiplomaType> getDiplomaTypeByNumberAndName(String number, String name);
}
