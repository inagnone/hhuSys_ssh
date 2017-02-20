package cjcx.service;

import cjcx.entity.Cj;
import cjcx.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
public interface CjService {

    public List<Cj> listCj(int page, int rows,String stuname,String idNumber,String examNumber);

    public List<Cj> lisCj(String studentName,String idNumber,String examNumber);

    public List<Cj> ListCj(Map<String,String> params,int page,int rows);

    public Cj updateCj(User user, int id, Cj cj, HashMap<String, Float> socres);

    public void addCj(User user,List<Cj> cjs);

    public Cj addCj(User user,Cj cj);

    public Long showNumberOfCj(String stuname,String idNumber,String examNumber);

    public void deleteCj(User user,int[] ids) throws Exception;
}
