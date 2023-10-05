package com.jn.web;

import com.jn.bean.User;
import com.jn.dao.impl.BaseDao;
import com.jn.service.UserService;
import com.jn.service.impl.UserServiceImpl;
import com.jn.utils.WebUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        User loginUser = userService.login(new User(null, user.getUsername(), user.getPassword(), null));
        // 如果等于 null,说明登录失败!
        if (loginUser == null) {
            //把错误信息和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());
            // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面 login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、销毁 Session 中用户登录的信息（或者销毁 Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1、获取请求参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //2、检查验证码是否正确===写死，要求验证码为abcde
        if(token != null && token.equalsIgnoreCase(user.getCode())){
            //3、检查用户名是否可用
            if(userService.existsUsername(user.getUsername())){
                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("password",user.getPassword());
                req.setAttribute("email",user.getEmail());
                req.setAttribute("code",user.getCode());
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                //用户名可用。调用service保存到数据库
                userService.registUser(new User(null,user.getUsername(),user.getPassword(),user.getEmail()));
                req.getSession().setAttribute("user",user);
                // 跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        }else{
            //跳回注册页面，跳回注册页面
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());
            req.setAttribute("email",user.getEmail());
            req.setAttribute("code",user.getCode());
            //System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
