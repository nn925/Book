package com.jn.test;

import com.jn.bean.User;
import com.jn.dao.UserDao;
import com.jn.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsername("admin11") == null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsernameAndPassword("admin","admin1") == null){
            System.out.println("用户名或密码不正确！");
        }else{
            System.out.println("用户名和密码输入正确！");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null,"test","test","test@123.com")));
    }
}