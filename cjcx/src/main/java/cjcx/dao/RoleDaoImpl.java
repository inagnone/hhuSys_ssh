package cjcx.dao;

import cjcx.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDao implements RoleDao {

    public void addRole(Role role) {
        this.getHibernateTemplate().save(role);
    }

    public void updateRole(Role role) {
        this.getHibernateTemplate().update(role);
    }

    public void deleteRole(Role role) {
        this.getHibernateTemplate().delete(role);
    }

    public Role getRoleById(int id) {
        return this.getHibernateTemplate().get(Role.class, id);
    }

    public List<Role> getRoles() {
        return (List<Role>) this.getHibernateTemplate().find("from Role");
    }
}
