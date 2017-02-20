package cjcx.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/2/17.
 */
@Entity
public class Role {
    private int id;
    private String name;
    private int newCj;
    private int updateCj;
    private int deleteCj;
    private int newUser;
    private int deleteUser;
    private int updateUser;
    private int newRole;
    private int updateRole;
    private int deleteRole;
    private int newExam;
    private int updateExam;
    private int deleteExam;

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
    @Column(name = "newCj", nullable = false)
    public int getNewCj() {
        return newCj;
    }

    public void setNewCj(int newCj) {
        this.newCj = newCj;
    }

    @Basic
    @Column(name = "updateCj", nullable = false)
    public int getUpdateCj() {
        return updateCj;
    }

    public void setUpdateCj(int updateCj) {
        this.updateCj = updateCj;
    }

    @Basic
    @Column(name = "deleteCj", nullable = false)
    public int getDeleteCj() {
        return deleteCj;
    }

    public void setDeleteCj(int deleteCj) {
        this.deleteCj = deleteCj;
    }

    @Basic
    @Column(name = "newUser", nullable = false)
    public int getNewUser() {
        return newUser;
    }

    public void setNewUser(int newUser) {
        this.newUser = newUser;
    }

    @Basic
    @Column(name = "deleteUser", nullable = false)
    public int getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(int deleteUser) {
        this.deleteUser = deleteUser;
    }

    @Basic
    @Column(name = "updateUser", nullable = false)
    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "newRole", nullable = false)
    public int getNewRole() {
        return newRole;
    }

    public void setNewRole(int newRole) {
        this.newRole = newRole;
    }

    @Basic
    @Column(name = "updateRole", nullable = false)
    public int getUpdateRole() {
        return updateRole;
    }

    public void setUpdateRole(int updateRole) {
        this.updateRole = updateRole;
    }

    @Basic
    @Column(name = "deleteRole", nullable = false)
    public int getDeleteRole() {
        return deleteRole;
    }

    public void setDeleteRole(int deleteRole) {
        this.deleteRole = deleteRole;
    }

    @Basic
    @Column(name = "newExam", nullable = false)
    public int getNewExam() {
        return newExam;
    }

    public void setNewExam(int newExam) {
        this.newExam = newExam;
    }

    @Basic
    @Column(name = "updateExam", nullable = false)
    public int getUpdateExam() {
        return updateExam;
    }

    public void setUpdateExam(int updateExam) {
        this.updateExam = updateExam;
    }

    @Basic
    @Column(name = "deleteExam", nullable = false)
    public int getDeleteExam() {
        return deleteExam;
    }

    public void setDeleteExam(int deleteExam) {
        this.deleteExam = deleteExam;
    }

    public boolean hasPrivilege(String actionName){
        if("newCj".equals(actionName)){
            return this.getNewCj()>0;
        }else if("updateCj".equals(actionName)){
            return this.getUpdateCj()>0;
        }else if("deleteCj".equals(actionName)){
            return this.getDeleteCj()>0;
        }else if("newExam".equals(actionName)){
            return this.getNewExam()>0;
        }else if("updateExam".equals(actionName)){
            return this.getUpdateExam()>0;
        }else if("deleteExam".equals(actionName)){
            return this.getDeleteExam()>0;
        }else if("newRole".equals(actionName)){
            return this.getNewRole()>0;
        }else if("updateRole".equals(actionName)){
            return this.getUpdateRole()>0;
        }else if("deleteRole".equals(actionName)){
            return this.getDeleteRole()>0;
        }else if("newUser".equals(actionName)){
            return this.getNewUser()>0;
        }else if("updateUser".equals(actionName)){
            return this.getUpdateUser()>0;
        }else if("deleteUser".equals(actionName)) {
            return this.getDeleteUser() > 0;
        }else{
            return false;
        }
    }

    public void copy(Role role){
        this.name = role.getName();
        this.newUser = role.getNewUser();
        this.updateUser = role.getUpdateUser();
        this.deleteUser = role.getDeleteUser();
        this.newCj = role.getNewCj();
        this.updateCj = role.getUpdateCj();
        this.deleteCj = role.getDeleteCj();
        this.newExam = role.getNewExam();
        this.updateExam = role.getUpdateExam();
        this.deleteExam = role.getDeleteExam();
        this.newRole = role.newRole;
        this.updateRole = role.updateRole;
        this.deleteRole = role.deleteRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (newCj != role.newCj) return false;
        if (updateCj != role.updateCj) return false;
        if (deleteCj != role.deleteCj) return false;
        if (newUser != role.newUser) return false;
        if (deleteUser != role.deleteUser) return false;
        if (updateUser != role.updateUser) return false;
        if (newRole != role.newRole) return false;
        if (updateRole != role.updateRole) return false;
        if (deleteRole != role.deleteRole) return false;
        if (newExam != role.newExam) return false;
        if (updateExam != role.updateExam) return false;
        if (deleteExam != role.deleteExam) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + newCj;
        result = 31 * result + updateCj;
        result = 31 * result + deleteCj;
        result = 31 * result + newUser;
        result = 31 * result + deleteUser;
        result = 31 * result + updateUser;
        result = 31 * result + newRole;
        result = 31 * result + updateRole;
        result = 31 * result + deleteRole;
        result = 31 * result + newExam;
        result = 31 * result + updateExam;
        result = 31 * result + deleteExam;
        return result;
    }
}
