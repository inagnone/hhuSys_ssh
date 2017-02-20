package cjcx.service;

import cjcx.annotation.PrivilegeInfo;
import cjcx.dao.CjDao;
import cjcx.entity.Cj;
import cjcx.entity.Grade;
import cjcx.entity.Test;
import cjcx.entity.User;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/7.
 */
@Service("cjService")
public class CjServiceImpl implements CjService {

    @Resource(name = "cjDao")
    private CjDao cjDao;

    public List<Cj> listCj(int page, int rows,String stuname,String idNumber,String examNumber) {
        return cjDao.getCjs(page,rows,stuname,idNumber,examNumber);
    }

    public List<Cj> lisCj(String studentName, String idNumber, String examNumber) {
        return cjDao.getCjs(studentName,idNumber,examNumber);
    }

    public List<Cj> ListCj(Map<String, String> params, int page, int rows) {
        return null;
    }

    public Cj getCjById(int id) {
        return cjDao.getCjById(id);
    }

    @PrivilegeInfo(name = "updateCj")
    public Cj updateCj(User user, int id, Cj cj, HashMap<String, Float> socres) {
        Cj cj1 = getCjById(id);
        cj1.resetValue(cj);
        Set<Test> tests = cj1.getExam().getTests();
        HashMap<String,Test> testMap = new HashMap<String, Test>();
        for(Test t:tests){
            testMap.put(t.getName(),t);
        }
        boolean isContain = false;
        for(String testName:socres.keySet()){
            for(Grade g:cj1.getGrades()){
                if(g.getTest().getName().equals(testName)){
                    g.setSocre(socres.get(testName));
                    isContain = true;
                    break;
                }
            }
            if(!isContain){
                Grade g = new Grade();
                g.setCj(cj1);
                g.setSocre(socres.get(testName));
                g.setTest(testMap.get(testName));
                cj1.getGrades().add(g);
            }
            isContain = false;
        }
        cjDao.updateCj(cj1);
        return cjDao.getCjById(cj1.getId());
    }

    @PrivilegeInfo(name = "newCj")
    public void addCj(User user,List<Cj> cjs) {
        cjDao.addCjs(cjs);
    }

    @PrivilegeInfo(name = "newCj")
    public Cj addCj(User user,Cj cj) {
        int id =  cjDao.addCj(cj);
        return cjDao.getCjById(id);
    }

    public Long showNumberOfCj(String stuname,String idNumber,String examNumber){
        return cjDao.showNumberOfCj(stuname,idNumber,examNumber);
    }

    @PrivilegeInfo(name = "deleteCj")
    public void deleteCj(User user,int[] ids) throws Exception{
        for(int id:ids){
            Cj cj = cjDao.getCjById(id);
            cjDao.deleteCj(cj);
        }
    }
}
