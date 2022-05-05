package com.ysy.pagedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpServletRequest request) {
        //具体业务判断
        if (!StringUtils.isEmpty(username) && password.equals("1")) {
          request.getSession().setAttribute("username",username);
            return "redirect:/main.html";
            //return "dashboard";
        } else {
            //告诉用户，登录失败
            model.addAttribute("msg", "用户名或者密码错误！");
            return "index";
        }
    }
    @RequestMapping("/user/signout")
    public String signout(HttpServletRequest request){
        //清除掉session或者把username的值置空
        //request.getSession().invalidate();
        request.getSession().setAttribute("username",null);
        return "index";
    }

}
