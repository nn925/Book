package com.jn.test;

import com.jn.bean.User;
import com.jn.service.UserService;
import com.jn.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"test","test1","111@111.com"));
    }

    @Test
    public void login() {
        if(userService.login(new User(null,"test","test1","111@111.com")) == null){
            System.out.println("用户名或密码输入错误，登录失败");
        }else{
            System.out.println("登录成功");
        }
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("test11")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名不存在");
        }
    }
}