package cjcx.action;

import cjcx.entity.Role;
import cjcx.entity.form.LoginForm;
import cjcx.entity.form.RegistFrom;
import cjcx.entity.User;
import cjcx.service.RoleService;
import cjcx.service.UserService;
import com.octo.captcha.service.CaptchaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 执行用户相关操作的控制器
 */
@Controller()
@SessionAttributes("currUser")
@RequestMapping({"/user"})
public class UserController{

    @ModelAttribute("loginForm")
    public LoginForm buildUserForm(){
        return new LoginForm();
    }

    @ModelAttribute("registFrom")
    public RegistFrom buildRegistForm(){
        return new RegistFrom();
    }

    @Resource(name="imageCaptchaService")
    private CaptchaService captchaService;

    @Resource(name="userService")
    private UserService us;

    @Resource(name = "roleService")
    private RoleService roleService;


    /**
     * 跳转到登陆页面
     * url：get（cjcx/user || cjcx）
     * @param loginForm
     * @return
     */
    @GetMapping()
    public String index(@ModelAttribute("loginForm") LoginForm loginForm,
                        RedirectAttributes attr,
                        Model model) {
        if(attr.getFlashAttributes().containsKey("msg")){
            model.addAttribute("msg",attr.getFlashAttributes().get("msg"));
        }
        return "/user/loginForm";
    }

    /**
     * 接收登陆表单，验证用户输入信息
     * url：post（cjcx/user/login.action || cjcx/login.action）
     * @param loginForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping("login")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpSession session){
        if(!captchaService.validateResponseForID(session.getId(), loginForm.getValidpassword())){
            bindingResult.addError(new ObjectError("validpassword","验证码不正确"));
            return "user/loginForm";
        }else if(bindingResult.hasErrors()){
            return "user/loginForm";
        }
        User user = us.getUser(loginForm.getUser());
        if(user == null){
            bindingResult.addError(new ObjectError("","用户不存在或用户名密码不匹配"));
              return  "user/loginForm";
        }
        session.setAttribute("userId",user.getId());
        return "redirect:/cj";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        return "user/registration";
    }

    @PostMapping("/registration")
    public String create(@Valid RegistFrom registFrom,
                         BindingResult bindingResult,
                         HttpSession session,
                         RedirectAttributes attr,
                         Model model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        if(!captchaService.validateResponseForID(session.getId(), registFrom.getValidnumber())){
            bindingResult.addError(new ObjectError("validpassword","验证码不正确"));
            return "user/registration";
        }else if(bindingResult.hasErrors()){
            return "user/registration";
        }
        if(!registFrom.passwordEqualtValidPassword()){
            bindingResult.addError(new ObjectError("","两次密码输入不一致"));
            return "user/registration";
        }

        User user = registFrom.getUser();
        us.addUser(user);
        attr.addFlashAttribute("msg","注册成功");
        System.out.println("注册成功");
        return "redirect:/user";
    }

    @GetMapping("/{userId}/edit")
    public String edit() {
        return null;
    }

    @PostMapping("/{userId}")
    public String update() {
        return null;
    }

    @GetMapping("/{userId}")
    public String show() {
        return null;
    }

    @DeleteMapping("/{userId}")
    public String delete() {
        return null;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
}
