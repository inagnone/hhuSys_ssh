package cjcx.entity.form;

import cjcx.entity.Role;
import cjcx.entity.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2016/12/30.
 */
public class RegistFrom {

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8,message = "密码长度不能少于8位")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String validpassword;

    @NotBlank(message = "验证码不能为空")
    private String validnumber;

    private int role;

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

    public String getValidnumber() {
        return validnumber;
    }

    public void setValidnumber(String validnumber) {
        this.validnumber = validnumber;
    }

    public boolean passwordEqualtValidPassword(){
        return password.equals(validpassword);
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public User getUser(){
        return new User(name,password,role);
    }
}
