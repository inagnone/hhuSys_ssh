package cjcx.action;

import cjcx.Exception.TooMuchDataException;
import cjcx.entity.Diploma;
import cjcx.entity.DiplomaForm;
import cjcx.entity.User;
import cjcx.service.DiplomaService;
import cjcx.service.ExcelService;
import cjcx.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jxl.read.biff.BiffException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */
@Controller
@RequestMapping("diploma")
public class DiplomaController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "diplomaService")
    private DiplomaService diplomaService;

    @Resource(name = "excelService")
    private ExcelService excelService;

    @Resource(name = "cacheManager")
    private CacheManager cacheManager;

    @GetMapping()
    public String index(){
        return "diploma/diploma";
    }

    @GetMapping("list")
    @ResponseBody
    public JSONObject list(HttpServletRequest request){
        JSONObject result = new JSONObject();
        Map parameterMap = request.getParameterMap();
        String page = (String) parameterMap.get("page");
        parameterMap.remove("page");
        String rows = (String) parameterMap.get("rows");
        parameterMap.remove("rows");
        List diplomas = diplomaService.listDiploma(Integer.valueOf(page), Integer.valueOf(rows), parameterMap);
        long sum = diplomaService.showNumberOfDiploma(parameterMap);
        result.put("rows",diplomas);
        result.put("pageNumber",page);
        result.put("total",sum);
        return result;
    }

    @ResponseBody
    @PostMapping("new")
    public JSONObject newDiploma(@ModelAttribute DiplomaForm diplomaform, HttpServletRequest  request, MultipartFile file){
        JSONObject result = new JSONObject();
//        int id = (Integer) request.getSession().getAttribute("userId");
        int id = 0;
        User user = userService.getUserById(id);
        try{
            Diploma diploma = diplomaService.addDiploma(user, diplomaform);
            result.put("success",true);
            result.put("rows",diploma);
        }catch (Exception e){
            result.put("success",false);
            result.put("message","程序错误");
        }
        return result;
    }

    @ResponseBody
    @GetMapping("job")
    public JSONArray getDiplomaJobs(String number){
        JSONArray result = new JSONArray();
        List<String> jobs = diplomaService.getDiplomaJobs(number);
        for(String j:jobs){
            JSONObject job = new JSONObject();
            job.put("name",j);
            job.put("job",j);
            result.add(job);
        }
        return result;
    }

    @PostMapping("excel")
    @ResponseBody
    public JSONObject readExcel(@RequestParam("file") MultipartFile file,HttpSession session){
        JSONObject result = new JSONObject();
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                List<DiplomaForm> diplomas = excelService.readDiplomaExcel(inputStream);
                System.out.println("sessId:"+session.getId());
                cacheManager.getCache("excelCache").put(session.getId(),diplomas);
                result.put("success",true);
                return result;
            } catch (IOException e) {
                result.put("success",false);
                result.put("message","文件读取错误，请检查上传文件内容及文件格式");
            } catch (BiffException e) {
                result.put("success",false);
                result.put("message","文件读取错误，请检查上传文件内容及文件格式");
            } catch (ParseException e) {
                result.put("success",false);
                result.put("message","文件读取错误，请检查上传文件内容及文件格式");
            } catch (java.text.ParseException e) {
                result.put("success",false);
                result.put("message","文件读取错误，请检查上传文件内容及文件格式");
            }catch (TooMuchDataException e){
                result.put("success",false);
                result.put("message","每次从Excel文件读取信息不允许超过500条");
            }
        }
        return result;
    }

    @GetMapping("validExcel")
    public String validExcelDiploma(){
        return "diploma/validExcel";
    }

    @ResponseBody
    @GetMapping("ajaxExcelContent")
    public List<DiplomaForm> ajaxExcelContent(HttpSession session){
        System.out.println("sessId:"+session.getId());
        Cache.ValueWrapper valueWrapper = cacheManager.getCache("excelCache").get(session.getId());
        List<DiplomaForm> diplomaForms = (List<DiplomaForm>) valueWrapper.get();
        return diplomaForms;
    }

    @PostMapping("newFromExcel")
    @ResponseBody
    public JSONObject ajaxCreateDiplomas(HttpServletRequest request){

        JSONObject result = new JSONObject();
//        int id = (Integer) request.getSession().getAttribute("userId");
        int id = 0;
        User user = userService.getUserById(id);
        String diplomas = request.getParameter("diplomas");
        List<Diploma> diplomaList = JSON.parseArray(diplomas, Diploma.class);
        try{
            diplomaService.addDiploma(user,diplomaList);
            result.put("success",true);
        }catch (Exception e){
            result.put("success",false);
            result.put("message","信息导入失败，请重试");
        }
        return result;
    }

}
