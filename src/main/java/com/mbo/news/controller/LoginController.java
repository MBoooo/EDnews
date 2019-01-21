package com.mbo.news.controller;

import com.mbo.news.entity.User;
import com.mbo.news.service.UserService;
import com.mbo.news.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Map;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-19 14:15
 **/
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String test(){
        return "login";
    }


    @RequestMapping("/dologin")
    public String login(@RequestParam String username,
                              @RequestParam String password,
                              ModelMap map) {

        if (username == null || password == null) {
            map.addAttribute("msg", ResponseUtil.badArgumentValue());
            return "error";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            map.addAttribute("msg",ResponseUtil.relogin());
            return "error";
        } catch (UnknownAccountException uae) {
            map.addAttribute("msg", ResponseUtil.relogin());
            return "error";
        }
        List<User> userList = userService.getUserByName(username);
        User user = userList.get(0);
        subject.getSession().setAttribute("user",user);
        return "b_index";
    }
    @RequestMapping("loginOut")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().removeAttribute("user");
        return "b_index";
    }
}
