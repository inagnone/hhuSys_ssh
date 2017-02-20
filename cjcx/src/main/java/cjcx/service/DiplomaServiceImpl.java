package cjcx.service;

import cjcx.dao.DiplomaDao;
import cjcx.dao.DiplomaTypeDao;
import cjcx.entity.Diploma;
import cjcx.entity.DiplomaForm;
import cjcx.entity.DiplomaType;
import cjcx.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */
@Service("diplomaService")
public class DiplomaServiceImpl implements DiplomaService {

    @Resource(name = "diplomaDao")
    private DiplomaDao diplomaDao;

    @Resource(name = "diplomaTypeDao")
    private DiplomaTypeDao diplomaTypeDao;

    public List<Diploma> listDiploma(int page, int rows,Map<String,String> params) {
        return diplomaDao.getDiplomas(page,rows,params);
    }

    public List<Diploma> lisDiploma(String studentName, String idNumber, String examNumber) {
        return null;
    }

    public List<Diploma> ListDiploma(Map<String, String> params, int page, int rows) {
        return null;
    }

    public Diploma updateDiploma(User user, int id, Diploma Diploma, HashMap<String, Float> socres) {
        return null;
    }

    public void addDiploma(User user, List<Diploma> diplomas) {
        diplomaDao.addDiplomas(diplomas);
    }

    public Diploma addDiploma(User user, DiplomaForm diplomaForm) {

        Diploma diploma = diplomaForm.createDiploma();
        DiplomaType diplomaType = diplomaTypeDao.getDiplomaTypebyNumber(diplomaForm.getDiplomaTypeNumber());
        diploma.setDiplomaType(diplomaType);
        int id = diplomaDao.addDiploma(diploma);
        Diploma diplomaInDb = diplomaDao.getDiplomaById(id);
        return diplomaInDb;
    }

    public Long showNumberOfDiploma(Map<String,String> params) {
        return diplomaDao.showNumberOfDiploma(params);
    }

    public void deleteDiploma(User user, int[] ids) throws Exception {

    }

    public List<String> getDiplomaJobs(String number){
        List<String> jobs = diplomaDao.getDiplomaJobs(number);
        return jobs;
    }
}
