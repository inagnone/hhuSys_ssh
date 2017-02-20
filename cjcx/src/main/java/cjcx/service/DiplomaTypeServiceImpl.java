package cjcx.service;

import cjcx.dao.DiplomaDao;
import cjcx.dao.DiplomaTypeDao;
import cjcx.entity.DiplomaType;
import cjcx.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
@Service("diplomaTypeService")
public class DiplomaTypeServiceImpl implements DiplomaTypeService {

    @Resource(name = "diplomaTypeDao")
    private DiplomaTypeDao diplomaTypeDao;

    public void addDiplomaType(User user, DiplomaType diplomaType) {

    }

    public void updateDiplomaType(User user, DiplomaType diplomaType) {

    }

    public void deleteDiplomaType(User user, DiplomaType diplomaType) {

    }

    public void deleteDiplomaTypes(User user, String[] numbers) {

    }

    public List<DiplomaType> listDiplomaType() {
        return diplomaTypeDao.getDiplomaTypes();
    }

    public DiplomaType getDiplomaTypeByNumber(String Number) {
        return null;
    }

    public List<DiplomaType> getDiplomaTypeByNumberAndName(String number, String name) {
        return diplomaTypeDao.getDiplomaTypes();
    }
}
