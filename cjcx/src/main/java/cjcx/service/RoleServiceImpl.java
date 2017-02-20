package cjcx.service;

import cjcx.annotation.PrivilegeInfo;
import cjcx.dao.RoleDao;
import cjcx.entity.Role;
import cjcx.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @PrivilegeInfo(name = "newRole")
    public void addRole(User user, Role role) {
        roleDao.addRole(role);
    }

    @PrivilegeInfo(name = "updateRole")
    public void updateRole(User user,Role role) {
        Role roleInDb = roleDao.getRoleById(role.getId());
        roleInDb.copy(role);
        roleDao.updateRole(roleInDb);
    }

    @PrivilegeInfo(name = "deleteRole")
    public void deleteRole(User user,int[] ids) {
        for(int id:ids){
            Role role = roleDao.getRoleById(id);
            roleDao.deleteRole(role);
        }
    }

    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
