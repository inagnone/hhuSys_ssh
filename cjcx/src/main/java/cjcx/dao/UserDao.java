package cjcx.dao;

import cjcx.entity.User;

/**
 * Created by Administrator on 2016/12/21.
 */
public interface UserDao {

    /**
     * 获取用户
     * @param user
     * @return
     */
    public User getUser(User user);

    public User getUserById(int id);

    /**
     * 添加用户
     * @param user
     */
    public void saveUser(User user);

    /**
     * 修改用户
     * @param user
     */
    public void updateUser(User user);

    /**
     * 通过用户名查找用户
     * @param name
     */
    public User getUserbyName(String name);
}
