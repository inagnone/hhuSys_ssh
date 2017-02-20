package cjcx.service;

import cjcx.entity.Diploma;
import cjcx.entity.DiplomaForm;
import cjcx.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */
public interface DiplomaService {

    public List<Diploma> listDiploma(int page, int rows, Map<String,String> params);

    public List<Diploma> lisDiploma(String studentName,String idNumber,String examNumber);

    public List<Diploma> ListDiploma(Map<String,String> params, int page, int rows);

    public Diploma updateDiploma(User user, int id, Diploma Diploma, HashMap<String, Float> socres);

    public void addDiploma(User user,List<Diploma> Diplomas);

    public Diploma addDiploma(User user,DiplomaForm diplomaForm);

    public Long showNumberOfDiploma(Map<String,String> params);

    public void deleteDiploma(User user,int[] ids) throws Exception;

    public List<String> getDiplomaJobs(String number);
}
