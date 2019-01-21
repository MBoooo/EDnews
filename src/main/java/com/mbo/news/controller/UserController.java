package com.mbo.news.controller;

import com.mbo.news.dao.UserJpaDao;
import com.mbo.news.entity.Roles;
import com.mbo.news.entity.User;
import com.mbo.news.service.RoleService;
import com.mbo.news.service.UserService;
import com.mbo.news.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: news
 * @description: 用户控制层
 * @author: mbo
 * @create: 2018-12-17 22:32
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping("/list")
    public String userList(ModelMap map) {
        List<User> userList = userService.findAll();
        List<String> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        for (User user : userList) {
            Roles roles = roleService.findOneById(user.getRoleId());
            roleList.add(roles.getRoleName());
        }
        List<Roles> rolesList = roleService.findAll();
        for(Roles roles : rolesList){
            roleNameList.add(roles.getRoleName());
        }
        map.addAttribute("userList", userList);
        map.addAttribute("roleList", roleList);
        map.addAttribute("roleNameList",roleNameList);
        map.addAttribute("user",new User());
        return "user";
    }

    @PostMapping("/createOrUpdate")
    public String creatrOrUpdate(@ModelAttribute User user,
                                 HttpServletRequest request,
                                 ModelMap map){
        String roleName = request.getParameter("rolename");
        System.out.println(roleName);
        if (user.getId() == null) {
            Roles roles = roleService.findByRoleName(roleName);
            System.out.println(roles.getId());
            user.setRoleId(roles.getId());
            user.setAddTime(LocalDateTime.now());
            System.out.println(LocalDateTime.now().toString());
            userService.saveOrUpdate(user);
        }
        User us = userService.findOneById(user.getId());
        us.setUserName(user.getUserName());
        us.setPassword(user.getPassword());
        Roles role = roleService.findByRoleName(roleName);
        us.setRoleId(role.getId());
        userService.saveOrUpdate(us);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{userId}")
    public String deleUser(@PathVariable Integer userId,
                           ModelMap map){
//        if( userId == ""){
//            model.addAttribute("msg",ResponseUtil.serious());
//            return "error";
//        }
        userService.deleteUser(userId);
        return "redirect:/user/list";
    }

    @GetMapping("/getUser")
    public String createOrUpdate(@ModelAttribute User user,
                                 HttpServletRequest request,
                                 ModelMap map){

        List<String> roleList = new ArrayList<>();
        String roleName = request.getParameter("roleName");
        if (user.getUserName() != null && user.getUserName() != "") {
            List<User> userList = userService.getUserByName(user.getUserName());
            for(User us : userList){
                Roles roles = roleService.findOneById(us.getId());
                roleList.add(roles.getRoleName());
            }
            map.addAttribute("userList",userList);
            map.addAttribute("roleList",roleList);
            return "user";
        }else if (roleName != null && roleName != "") {
            Roles roles = roleService.findByRoleName(roleName);
            List<User> userList = userService.findByRoleId(roles.getId());

            for(User us : userList){
                roleList.add(roleName);
            }
            map.addAttribute("userList",userList);
            map.addAttribute("roleList",roleList);
            return "user";
        }
        return "redirect:/user/list";

    }


}
