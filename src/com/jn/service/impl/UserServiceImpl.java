package com.jn.service.impl;

import com.jn.bean.User;
import com.jn.dao.UserDao;
import com.jn.dao.impl.UserDaoImpl;
import com.jn.service.UserService;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * @author jiangna27602
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
