package cjcx.action;

import cjcx.Exception.DeleteExamWithCjException;
import cjcx.Exception.UserException;
import cjcx.entity.Exam;
import cjcx.entity.Test;
import cjcx.entity.User;
import cjcx.service.ExamService;
import cjcx.service.TestService;
import cjcx.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/12.
 */
@Controller
@RequestMapping("exam")
public class ExamController {

    @Resource(name = "examService")
    private ExamService examService;

    @Resource(name = "testService")
    private TestService testService;

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping
    public String index(){
        return "exam/exam";
    }

    @GetMapping("new")
    public String buildForm(){
        return "exam/examForm";
    }

    @ResponseBody
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    public String list(String number,String name){
        List<Exam> exams = examService.getExamByNumberAndName(number,name);
        return JSON.toJSONString(exams);
    }

    @ResponseBody
    @GetMapping(value = "/{examNumber}",produces = {"application/json;charset=UTF-8"})
    public Exam getExamByNumber(@PathVariable String examNumber){
        Exam exam = examService.getExamByNumber(examNumber);
        return exam;
    }

    @PostMapping("new")
    @ResponseBody
    public String addExam(@RequestParam String number,
                          @RequestParam String name,
                          @RequestParam String[] tests,
                          HttpServletRequest request){
        JSONObject obj = new JSONObject();
        Integer id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        boolean isEmpty = false;
        Set<Test> testSet = new HashSet<Test>();
        Exam exam = new Exam();
        exam.setNumber(number);
        exam.setName(name);
        if(tests != null){
            for(String testName:tests){
                Test test = new Test();
                isEmpty = StringUtils.isEmpty(test);
                if(!isEmpty){
                    test.setName(testName);
                    testSet.add(test);
                }
            }
        }
        if(StringUtils.isEmpty(number) || StringUtils.isEmpty(name) || isEmpty){
            obj.put("success",false);
            obj.put("message","字段不能为空");
            return obj.toJSONString();
        }
        exam.setTests(testSet);
        try {
            examService.addExam(user,exam);
        }catch (UserException e){
            obj.put("success",false);
            obj.put("message","您未登陆或没有相关操作权限");
        }
        obj.put("success",true);
        return obj.toJSONString();
    }

    @PostMapping("delete")
    @ResponseBody
    public JSONObject deleteExam(HttpServletRequest request,@RequestParam String ids){
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(ids)){
            result.put("success",false);
            result.put("message","没有选择有效信息，请刷新页面后重试");
            return result;
        }
        String[] numbers = ids.split(",");
        Integer id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        try {
            examService.deleteExams(user,numbers);
            result.put("success",true);
        }catch (DeleteExamWithCjException e){
            result.put("success",false);
            result.put("message","考试存在成绩数据，请先删除考试对应的学生成绩数据后在执行考试的删除操作。");
        }catch (NullPointerException e){
            result.put("success",false);
            result.put("message","未选择有效信息，请刷新页面后重新操作");
        }catch (UserException e){
            result.put("success",false);
            result.put("message","您未登陆或没有相关操作权限");
        }
        return result;
    }
}
