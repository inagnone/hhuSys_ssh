package cjcx.dao;

import cjcx.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public interface RoleDao {
    public void addRole(Role role);

    public void updateRole(Role role);

    public void deleteRole(Role role);

    public Role getRoleById(int id);

    public List<Role> getRoles();
}
