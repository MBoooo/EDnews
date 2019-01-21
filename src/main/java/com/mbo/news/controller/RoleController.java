package com.mbo.news.controller;

import com.mbo.news.entity.Category;
import com.mbo.news.entity.Roles;
import com.mbo.news.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-22 20:34
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String roleList(ModelMap map){
        List roleList = roleService.findAll();
        map.addAttribute("roleList",roleList);
        map.addAttribute("role",new Roles());
        return "role";
    }
    @RequestMapping(value = "/createOrUpdate",method = RequestMethod.POST)
    public String createOrUpdate(@ModelAttribute Roles roles){
        if(roles.getId() == null){
//            category.setCategoryName(cateName);
            roleService.creatrOrUpdate(roles);
        }
        Roles role = roleService.findOneById(roles.getId());
        role.setRoleName(roles.getRoleName());
        roleService.creatrOrUpdate(role);
        return "redirect:/role/list";
    }

    @GetMapping("/delete/{roleId}")
    public String deleUser(@PathVariable int roleId,
                           Model model){
//        if( userId == ""){
//            model.addAttribute("msg",ResponseUtil.serious());
//            return "error";
//        }
        roleService.delete(roleId);
        return "redirect:/role/list";
    }
}
