package cjcx.action;

import cjcx.entity.Test;
import cjcx.service.TestService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
@RequestMapping("test")
public class TestController {

    @Resource(name = "testService")
    private TestService testService;

    @GetMapping()
    public List<Test> listTests(){
        List<Test> tests = testService.getTests();
        return tests;
    }

    @PostMapping("newTest")
    public JSONObject addTest(@ModelAttribute Test test){
        JSONObject obj = new JSONObject();
        testService.addTest(test);
        obj.put("success",true);
        return obj;
    }
}
