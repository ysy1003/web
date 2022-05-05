package com.ysy.pagedemo.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录拦截器,只有去跳转或者访问配置拦截的url，才会触发拦截器
public class LoginHT implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录成功之后，从LoginController里面传入session
        Object loginUser = request.getSession().getAttribute("username");

        //System.out.println("-----debug:"+loginUser);

        //没有登录,拦截
        if (loginUser == null){
            request.setAttribute("msg","没有权限，请先登录");
            //转发页面到登录页
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
