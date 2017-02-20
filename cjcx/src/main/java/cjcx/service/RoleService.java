package cjcx.service;

import cjcx.entity.Role;
import cjcx.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public interface RoleService {

    public void addRole(User user, Role role);

    public void updateRole(User user,Role role);

    public void deleteRole(User user,int[] ids);

    public Role getRoleById(int id);

    public List<Role> getRoles();
}
