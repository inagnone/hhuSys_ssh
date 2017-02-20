package cjcx.action;

import cjcx.entity.DiplomaType;
import cjcx.service.DiplomaService;
import cjcx.service.DiplomaTypeService;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
@org.springframework.stereotype.Controller
@RequestMapping("diplomaType")
public class DiplomaTypeController {

    @Resource(name = "diplomaTypeService")
    private DiplomaTypeService diplomaTypeService;

    @GetMapping("list")
    @ResponseBody
    public List getDiplomaTypes(){
        List<DiplomaType> diplomaTypes = diplomaTypeService.listDiplomaType();
        return  diplomaTypes;
    }
}
