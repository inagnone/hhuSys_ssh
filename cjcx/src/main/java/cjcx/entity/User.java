package cjcx.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2016/12/22.
 */
@Entity
public class User {

    private int id;

    private String name;

    private String password;

    private Role role;

    private int roleId;

    public User(){}

    public User(String name,String password){
        this.name = name;
        this.password = password;
    }

    public User(String name,String password,Role role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name,String password,int roleId){
        this.name = name;
        this.password = password;
        this.roleId = roleId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
