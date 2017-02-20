package cjcx.service;

import cjcx.entity.User;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface UserService {

    public User getUser(User user);
    public void addUser(User user);
    public void updateUser(User user);
    public User getUserById(int id);
}
