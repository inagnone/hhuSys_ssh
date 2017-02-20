package cjcx.action;

import cjcx.Exception.UserException;
import cjcx.entity.*;
import cjcx.service.CjService;
import cjcx.service.ExamService;
import cjcx.service.ExcelService;
import cjcx.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/3.
 */
@Controller
@RequestMapping("cj")
public class CjController {

    @Resource(name = "cjService")
    private CjService cjService;

    @Resource(name = "examService")
    private ExamService examService;

    @Resource(name = "excelService")
    private ExcelService excelService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "cacheManager")
    private CacheManager cacheManager;

    @GetMapping
    public String index(Model model){
        List<Exam> exams = examService.listExam();
        model.addAttribute("exams",exams);
        return "cj/cj";
    }

    @PostMapping("cjs")
    @ResponseBody
    public JSONObject getCj(@RequestParam int page,
                            @RequestParam int rows,
                            @RequestParam String stuname,
                            @RequestParam String idNumber,
                            @RequestParam String examNumber){
        JSONObject result = new JSONObject();
        List<Cj> cjs = cjService.listCj(page, rows,stuname,idNumber,examNumber);
        long sum = cjService.showNumberOfCj(stuname,idNumber,examNumber);
        for(Cj c:cjs){
            c.setExam(null);
            Set<Grade> grades = c.getGrades();
            for(Grade g:grades){
                g.setCj(null);
            }
        }
        result.put("rows",cjs);
        result.put("pageNumber",page);
        result.put("total",sum);
        return result;
    }

    @GetMapping("new")
    public String newCj(@ModelAttribute Cj cj){
        return "cj/cjForm";
    }

    @PostMapping("new")
    @ResponseBody
    public JSONObject ajaxCreateCj(@Valid @ModelAttribute Cj cj, BindingResult bindingResult, HttpServletRequest request, @RequestParam String examNumber){
        JSONObject result = new JSONObject();
        int id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        boolean hasErrors = bindingResult.hasErrors();
        if(hasErrors){
            result.put("success",false);
            result.put("message",bindingResult.getAllErrors());
            return result;
        }else{
            Exam exam = examService.getExamByNumber(examNumber);
            Set<Test> tests = exam.getTests();
            cj.setGrades(new HashSet<Grade>());
            cj.setExam(exam);
            for(Test t:tests){
                String socreStr = request.getParameter(t.getName());
                Grade grade = new Grade();
                grade.setSocre(Float.parseFloat(socreStr));
                grade.setCj(cj);
                grade.setTest(t);
                cj.getGrades().add(grade);
            }
        }
        Cj newCj = cjService.addCj(user,cj);
        newCj.setExam(null);
        for(Grade g:newCj.getGrades()){
            g.setCj(null);
        }
        result.put("success",true);
        result.put("rows",newCj);
        return result;
    }

    @PostMapping("newFromExcel")
    @ResponseBody
    public JSONObject ajaxCreateCjs(HttpServletRequest request) throws UnsupportedEncodingException {

//        JSONArray array = JSONObject.parseArray(cjs);
        int id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        String cjs = request.getParameter("cjs");
        List<Cj> cjList = JSON.parseArray(cjs, Cj.class);
        cjService.addCj(user,cjList);
        return null;
    }

    @PostMapping("excel")
    @ResponseBody
    public JSONObject readExcel(@RequestParam("file") MultipartFile file,HttpSession session){
        JSONObject obj = new JSONObject();
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                List<Cj> cjs = excelService.readExcel(inputStream);
                Cj cj = cjs.get(0);
                Exam exam = cj.getExam();
                cacheManager.getCache("excelCache").put(session.getId(),cjs);
                cacheManager.getCache("examCache").put(session.getId(),exam);
                obj.put("success",true);
                return obj;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    @GetMapping(value = "allCjToExcel",produces = {"application/vnd.ms-excel;charset=UTF-8"})
    public void writeExcel(@RequestParam String stuname,
                           @RequestParam String idNumber,
                           @RequestParam String examNumber,
                           HttpServletRequest request,
                           HttpServletResponse response){
        Integer id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        List<Cj> cjs = cjService.lisCj(stuname, idNumber, examNumber);
        Exam exam = examService.getExamByNumber(examNumber);
        String[] title = {"序号","姓名","工作单位","身份证号","考试名称","考试批次","考试时间","施工企业成绩","水管单位成绩","项目法人成绩","专业能力成绩"};
        byte[] bytes = null ;
        try {
            bytes = excelService.writerExcel(user,cjs, exam, title);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        try {
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(( "成绩.xls").getBytes(), "iso-8859-1"));
            response.setContentLength(bytes.length);
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @GetMapping("ajaxExcelContent")
    public List<Cj> ajaxExcelContent(HttpSession session){
        Cache.ValueWrapper valueWrapper = cacheManager.getCache("excelCache").get(session.getId());
        List<Cj> cjs = (List<Cj>) valueWrapper.get();
        return cjs;
    }

    @ResponseBody
    @GetMapping("ajaxExcelExam")
    public Exam ajaxExcelExam(HttpSession session){
        Cache.ValueWrapper valueWrapper = cacheManager.getCache("examCache").get(session.getId());
        Exam exam = (Exam) valueWrapper.get();
        return exam;
    }

    @GetMapping("validExcel")
    public String validExcel(){
        return "cj/validExcel";
    }

    @PostMapping("edit")
    @ResponseBody
    public JSONObject updateCj(@ModelAttribute Cj cj,HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            Integer id = (Integer) request.getSession().getAttribute("userId");
            User user = userService.getUserById(id);
            Exam exam = examService.getExamByNumber(request.getParameter("examNumber"));
            cj.setExam(exam);
            HashMap<String, Float> socres = new HashMap<String, Float>();
            for (Test t : exam.getTests()) {
                String socreStr = request.getParameter(t.getName());
                socres.put(t.getName(), Float.valueOf(socreStr));
            }
            Cj newCj = cjService.updateCj(user,cj.getId(), cj, socres);
            newCj.setExam(null);
            for(Grade g:newCj.getGrades()){
                g.setCj(null);
            }
            result.put("success",true);
            result.put("rows",newCj);
            result.put("pageNumber",1);
            result.put("total",1);
        }catch (Exception e){
            result.put("success",false);
            result.put("message","操作异常，请重试");
        }
        return result;
    }

    @ResponseBody
    @PostMapping("delete")
    public JSONObject deleteCj(@RequestParam String id,
                               HttpServletRequest request){
        JSONObject result = new JSONObject();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(userId);
        if(StringUtils.isEmpty(id)){
            result.put("success",false);
            result.put("message","没有选择有效成绩，请刷新页面后重试");
        }
        try {
            String[] idstr = id.split(",");
            int[] ids = new int[idstr.length];
            for (int i =0;i<ids.length;i++){
                ids[i] = Integer.valueOf(idstr[i]);
            }
            cjService.deleteCj(user,ids);
            result.put("success",true);
        }catch (UserException e){
            result.put("success",false);
            result.put("message","您未登陆或没有相关操作权限");
        }catch (Exception e){
            result.put("success",false);
            result.put("message","操作异常，请刷新页面后重试");
        }
        return result;
    }
}
