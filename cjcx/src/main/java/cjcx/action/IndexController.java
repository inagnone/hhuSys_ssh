package cjcx.action;

import cjcx.entity.Exam;
import cjcx.service.ExamService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class IndexController {

    @Resource(name = "examService")
    private ExamService examService;

    @GetMapping()
    public String index(Model model){
        List<Exam> exams = examService.listExam();
        model.addAttribute("exams",exams);
        return "index";
    }
}
