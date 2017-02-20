package cjcx.entity.form;

import cjcx.entity.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2016/12/25.
 */
public class LoginForm {

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotNull(message = "密码不能为空")
    @Size(min = 8,message = "密码长度不能少于8位")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String validpassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidpassword() {
        return validpassword;
    }

    public void setValidpassword(String validpassword) {
        this.validpassword = validpassword;
    }

    public User getUser(){
        return new User(name,password);
    }
}
