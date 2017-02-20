package cjcx.service;

import cjcx.Exception.TooMuchDataException;
import cjcx.dao.ExamDao;
import cjcx.entity.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/28.
 */
@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

    @Resource(name = "examDao")
    private ExamDao examDao;

    public List<Cj> readExcel(InputStream fileInputStream) throws IOException, BiffException, ParseException {
        Workbook workbook = Workbook.getWorkbook(fileInputStream);
        int numberOfSheets = workbook.getNumberOfSheets();
        ArrayList<Cj> cjs = new ArrayList<Cj>();
        for(int i=0;i<numberOfSheets;i++){
            Sheet sheet = workbook.getSheet(i);
            Cell[] titleRow = sheet.getRow(1);
            String[] title = getTitle(titleRow);
            for(int row = 2;row<sheet.getRows();row++){
                Cj cj = new Cj();
                cj.setGrades(new HashSet<Grade>());
                for(int col=1;col<sheet.getColumns();col++){
                    Cell cell = sheet.getCell(col, row);
                    setParamter(cj,title[col],cell);
                }
                cjs.add(cj);
            }
        }
        return cjs;
    }

    public List<DiplomaForm> readDiplomaExcel(InputStream fileInputStream) throws IOException, BiffException, ParseException {
        Workbook workbook = Workbook.getWorkbook(fileInputStream);
        int numberOfSheets = workbook.getNumberOfSheets();
        ArrayList<DiplomaForm> diplomas = new ArrayList<DiplomaForm>();
        int sumRows = 0;
        for(int i=0;i<numberOfSheets;i++){
            Sheet sheet = workbook.getSheet(i);
            sumRows =+ sheet.getRows();
        }
        if(sumRows > 500){
            throw new TooMuchDataException("excel文件超过500条信息");
        }
        for(int i=0;i<numberOfSheets;i++){
            Sheet sheet = workbook.getSheet(i);
            Cell[] titleRow = sheet.getRow(1);
            String[] title = getTitle(titleRow);
            for(int row = 2;row<sheet.getRows();row++){
                DiplomaForm diploma = new DiplomaForm();
                for(int col=1;col<sheet.getColumns();col++){
                    Cell cell = sheet.getCell(col, row);
                    setParamterOfDiploma(diploma,title[col],cell);
                }
                diplomas.add(diploma);
            }
        }
        return diplomas;
    }

    public byte[] writerExcel(User user, List<Cj> cjs, Exam exam, String[] title) throws IOException, WriteException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        WritableWorkbook workbook = Workbook.createWorkbook(out);
        WritableSheet sheet = workbook.createSheet(exam.getName(), 0);
        //输入表头
        for(int i=0;i<title.length;i++){
            sheet.addCell(new Label(i,1,title[i]));
        }
        //输入成绩信息
        for(int i=0;i<cjs.size();i++){
            Label[] labels = setCellContent(i + 2, cjs.get(i), title);
            for(int j=0;j<labels.length;j++){
                Label label = labels[j];
                if(label != null){
                    sheet.addCell(label);
                }
            }
        }
        workbook.write();
        workbook.close();
        return out.toByteArray();
    }

    public String[] getTitle(Cell[] row){
        String[] titles = new String[row.length];
        for(int i=0;i<row.length;i++){
            titles[i] = row[i].getContents();
        }
        return titles;
    }

    /**
     * 根据标题获取单元格内容并复制到成绩对象
     * @param cj
     * @param title
     * @param cell
     * @throws ParseException
     */
    public void setParamter(Cj cj,String title,Cell cell) throws ParseException {
        if("姓名".equals(title)){
            cj.setStudentName(cell.getContents());
        }else if("工作单位".equals(title)){
            cj.setCompany(cell.getContents());
        }else if("身份证号".equals(title)){
            cj.setIdNumber(cell.getContents());
        }else if("考试名称".equals(title)){
            if(!StringUtils.isEmpty(cell.getContents())){
                Exam exam = examDao.getExambyNumber(cell.getContents());
                cj.setExam(exam);
            }
        }else if("考试批次".equals(title)){
            cj.setBatchNumber(cell.getContents());
        }else if("考试时间".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                cj.setExamDate(new Date(date.getTime()));
            }
        }else if(title.contains("成绩")){
            if(!StringUtils.isEmpty(cell.getContents())){
                Grade grade = new Grade();
                grade.setSocre(Float.parseFloat(cell.getContents()));
                Exam exam = cj.getExam();
                for(Test t:exam.getTests()){
                    if(title.equals(t.getName())){
                        grade.setTest(t);
                        break;
                    }
                }
                cj.getGrades().add(grade);
            }
        }
    }

    public void setParamterOfDiploma(DiplomaForm diploma,String title,Cell cell) throws ParseException {
        if("姓名".equals(title)){
            diploma.setName(cell.getContents());
        }else if("身份证号".equals(title)){
            diploma.setIdNumber(cell.getContents());
        }else if("企业名称".equals(title)){
            diploma.setCompany(cell.getContents());
        }else if("岗位名称".equals(title)){
            diploma.setJob(cell.getContents());
        }else if("证书编号".equals(title)){
            diploma.setDiplomaNumber(cell.getContents());
        }else if("批准日期".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setApprovetime(new Date(date.getTime()));
            }
        }else if("发证日期".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setIssuetime(new Date(date.getTime()));
            }
        }else if("有效期至".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setEndtime(new Date(date.getTime()));
            }
        }else if("有效期".equals(title)){
            diploma.setValidity(cell.getContents());
        }else if("培训证书类别编号".equals(title)){
            diploma.setDiplomaTypeNumber(cell.getContents());
        }
//        else if("培训证书名称".equals(title)){
//            diploma.setDiplomaTypeNumber(cell.getContents());
//        }
        else if("审批单位区域".equals(title)){
            diploma.setArea(cell.getContents());
        }else if("发证单位".equals(title)){
            diploma.setIssuecompany(cell.getContents());
        }else if("委托单位".equals(title)){
            diploma.setCommissionunit(cell.getContents());
        }else if("承办单位".equals(title)){
            diploma.setUndertaker(cell.getContents());
        }else if("学时数".equals(title)){
            diploma.setStudyhour(cell.getContents());
        }else if("培训开始日期".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setStarttime(new Date(date.getTime()));
            }
        }else if("培训结束日期".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setFinishtime(new Date(date.getTime()));
            }
        }else if("培训时间".equals(title)){
            diploma.setTrainhour(cell.getContents());
        }else if("考试时间".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setExamtime(new Date(date.getTime()));
            }
        }else if("培训方式".equals(title)){
            diploma.setTrainway(cell.getContents());
        }else if("培训机构".equals(title)){
            diploma.setTrainer(cell.getContents());
        }else if("第一次延期至".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setFirdelay(new Date(date.getTime()));
            }
        }else if("第二次延期至".equals(title)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String contents = cell.getContents();
            if(!StringUtils.isEmpty(contents)){
                java.util.Date date = sdf.parse(contents);
                diploma.setSecdelay(new Date(date.getTime()));
            }
        }else if("备注".equals(title)){
            diploma.setDetail(cell.getContents());
        }
    }

    /**
     * 将成绩对象各属性按照标题顺序分装成一个单元格数组
     * @param row   对应成绩属性在表格中的行数
     * @param cj    目标成绩对象
     * @param titles    标题数组
     * @return
     */
    private Label[] setCellContent(int row,Cj cj,String[] titles){
        Label[] labels = new Label[titles.length];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<titles.length;i++){
            Label label = null;
            if("序号".equals(titles[i])){
                label = new Label(i,row,String.valueOf(i));
            }else if("姓名".equals(titles[i])){
                label = new Label(i,row,cj.getStudentName());
            }else if("工作单位".equals(titles[i])){
                label = new Label(i,row,cj.getCompany());
            }else if("身份证号".equals(titles[i])){
                label = new Label(i,row,cj.getIdNumber());
            }else if("考试名称".equals(titles[i])){
                label = new Label(i,row,cj.getExam().getNumber());
            }else if("考试批次".equals(titles[i])){
                label = new Label(i,row,cj.getBatchNumber());
            }else if("考试时间".equals(titles[i])){
                Date date = cj.getExamDate();
                if(date != null){
                    label = new Label(i,row,format.format(date));
                }
            }else if(titles[i].contains("成绩")){
                Set<Grade> grades = cj.getGrades();
                for(Grade g:grades){
                    if(g.getTest().getName().equals(titles[i])){
                        label = new Label(i,row,String.valueOf(g.getSocre()));
                        break;
                    }
                }
            }
            labels[i] = label;
        }
        return labels;
    }

}
