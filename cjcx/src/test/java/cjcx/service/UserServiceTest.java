package cjcx.service;

import cjcx.dao.UserDao;
import cjcx.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/12/22.
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class UserServiceTest {

    @Resource(name="userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Test
    public void testsaveuser(){
        User user = new User();
        user.setName("demo");
        user.setPassword("123");
        user.setRole(roleService.getRoleById(0));
        userService.addUser(user);
    }

    @Test
    public void log4JTest(){
        Logger logger = Logger.getLogger(UserServiceTest.class);
        logger.debug("this is test");
    }
}
