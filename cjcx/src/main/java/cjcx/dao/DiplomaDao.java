package cjcx.dao;

import cjcx.entity.Diploma;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */
public interface DiplomaDao {
    public int addDiploma(Diploma Diploma);

    public void addDiplomas(List<Diploma> Diplomas);

    public Diploma getDiplomaById(int id);

    public List<Diploma> getDiplomabyStudentName(String name);

    public List<Diploma> getDiplomabyIdNumber(String idnumber);

    public List<Diploma> getDiplomas(int page, int rows, Map<String,String> params);

    public List<Diploma> getDiplomas(String studentName,String idNumber,String examNumber);

    public void updateDiploma(Diploma Diploma);

    public void deleteDiploma(Diploma Diploma);

    public Long showNumberOfDiploma(Map<String,String> params);

    public List<String> getDiplomaJobs(String number);
}
