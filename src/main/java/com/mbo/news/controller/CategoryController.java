package com.mbo.news.controller;

import com.mbo.news.entity.Category;
import com.mbo.news.entity.Roles;
import com.mbo.news.service.CategoryService;
import com.mbo.news.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-18 21:59
 **/

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String findAll(ModelMap map){
        List cateList = categoryService.findAll();
        map.addAttribute("cateList",cateList);
        map.addAttribute("category",new Category());
        return "category";
    }

//    @GetMapping("/get")
//    public String get(HttpServletRequest request,Model model){
//        String name = request.getParameter("name");
//        Category category = categoryService.findByCategoryName(name);
//        System.out.println(category.getCategoryName());
//        return  "category";
//    }


    @RequestMapping(value = "/createOrUpdate",method = RequestMethod.POST)
    public String createOrUpdate(@ModelAttribute Category category){
        Roles roles = new Roles();
        if(category.getId() == null){
//            category.setCategoryName(cateName);
            categoryService.createOrUpdate(category);
            roles.setRoleName(category.getCategoryName());
            roleService.creatrOrUpdate(roles);

        }
        Category cate = categoryService.findOneById(category.getId());
        cate.setCategoryName(category.getCategoryName());
        roles.setRoleName(cate.getCategoryName());
        roleService.creatrOrUpdate(roles);
        categoryService.createOrUpdate(cate);
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{cateId}")
    public String deleUser(@PathVariable int cateId,
                           Model model){
//        if( userId == ""){
//            model.addAttribute("msg",ResponseUtil.serious());
//            return "error";
//        }
        categoryService.delete(cateId);
        return "redirect:/category/list";
    }
}
