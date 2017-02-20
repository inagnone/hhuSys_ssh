package cjcx.service;

import cjcx.dao.RoleDao;
import cjcx.dao.UserDao;
import cjcx.entity.Role;
import cjcx.entity.User;
import cjcx.utils.PasswordHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Administrator on 2016/12/21.
 */
@Service("userService")
public class UserServiceImp implements UserService {

    @Resource(name="userDao")
    private UserDao userdao;

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    public User getUser(User user) {
        User userInDb = userdao.getUserbyName(user.getName());
        try {
            if(userInDb == null || !PasswordHash.validatePassword(user.getPassword(),userInDb.getPassword())){
                userInDb = null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return userInDb;
    }

    public User getUserById(int id){
       return  userdao.getUserById(id);
    }

    public void addUser(User user) {
        try {
            Role role = roleDao.getRoleById(user.getRoleId());
            user.setRole(role);
            String hash = PasswordHash.createHash(user.getPassword());
            user.setPassword(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        userdao.saveUser(user);
    }

    public void updateUser(User user) {
        userdao.updateUser(user);
    }
}
