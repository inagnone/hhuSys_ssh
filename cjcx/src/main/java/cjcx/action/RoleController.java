package cjcx.action;

import cjcx.Exception.UserException;
import cjcx.entity.Role;
import cjcx.entity.User;
import cjcx.service.RoleService;
import cjcx.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping()
    public String index(){
        return "role/role";
    }

    @ResponseBody
    @GetMapping("list")
    public List<Role> list(){
        return roleService.getRoles();
    }

    @ResponseBody
    @PostMapping("new")
    public JSONObject addRole(@ModelAttribute Role role, HttpServletRequest request){
        JSONObject result = new JSONObject();
        Integer id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        try{
            roleService.addRole(user,role);
            result.put("success",true);
        }catch (UserException e){
            result.put("success",false);
            result.put("message","您未登陆或没有相关操作权限");
        }
        return result;
    }

    @ResponseBody
    @PostMapping("edit")
    public JSONObject editRole(@ModelAttribute Role role,HttpServletRequest request){
        JSONObject result = new JSONObject();
        Integer id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        try {
            roleService.updateRole(user,role);
            result.put("success",true);
        }catch (UserException e){
            result.put("success",false);
            result.put("message","您未登陆或没有相关操作权限");
        }
        return result;
    }

    @ResponseBody
    @PostMapping("delete")
    public JSONObject deleteRole(HttpServletRequest request,@RequestParam String ids){
        JSONObject result = new JSONObject();
        Integer id = (Integer) request.getSession().getAttribute("userId");
        User user = userService.getUserById(id);
        if(StringUtils.isEmpty(ids)){
            result.put("success",false);
            result.put("message","没有选择有效权限，请刷新页面后重试");
        }
        String[] idsStr = ids.split(",");
        int[] idInt = new int[idsStr.length];
        for(int i=0;i<idsStr.length;i++){
            idInt[i] = Integer.valueOf(idsStr[i]);
        }
        try{
            roleService.deleteRole(user,idInt);
            result.put("success",true);
        }catch (UserException e){
            result.put("success",false);
            result.put("message","您未登陆或没有相关操作权限");
        }
        return result;
    }
}
