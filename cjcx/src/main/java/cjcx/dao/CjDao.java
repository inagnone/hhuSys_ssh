package cjcx.dao;

import cjcx.entity.Cj;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public interface CjDao {
    /**
     * 添加成绩
     * @param cj
     * @return
     */
    public int addCj(Cj cj);

    /**
     * 批量添加成绩
     * @param cjs
     */
    public void addCjs(List<Cj> cjs);

    /**
     * 通过id查找成绩
     * @param id
     * @return
     */
    public Cj getCjById(int id);

    /**
     * 通过学生名称查找成绩
     * @param name
     * @return
     */
    public List<Cj> getCjbyStudentName(String name);

    /**
     * 通过学生身份证查找成绩
     * @param idnumber
     * @return
     */
    public List<Cj> getCjbyIdNumber(String idnumber);

    /**
     * 通过参数查找成绩并提供分页
     * @param page  当前页数
     * @param rows  每页显示成绩数量
     * @param stuname   查找参数，学生名称
     * @param idNumber  查找参数，学生身份证
     * @param examNumber    查找参数，考试编号
     * @return  与参数相匹配的成绩集合
     */
    public List<Cj> getCjs(int page,int rows,String stuname,String idNumber,String examNumber);

    public List<Cj> getCjs(String studentName,String idNumber,String examNumber);

    /**
     * 更新成绩
     * @param cj
     */
    public void updateCj(Cj cj);

    /**
     * 删除成绩
     * @param cj
     */
    public void deleteCj(Cj cj);

    /**
     * 统计与查找参数相匹配的成绩数量
     * @param stuname   查找参数，学生名称
     * @param idNumber  查找参数，学号是身份证
     * @param examNumber    查找参数，考试编号
     * @return  成绩数量
     */
    public Long showNumberOfCj(String stuname,String idNumber,String examNumber);

}
