package com.mbo.news.controller;


import com.baidu.ueditor.ActionEnter;
import com.mbo.news.entity.Category;
import com.mbo.news.entity.News;
import com.mbo.news.entity.Roles;
import com.mbo.news.entity.User;
import com.mbo.news.service.CategoryService;
import com.mbo.news.service.NewsService;
import com.mbo.news.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldb on 2017/4/9.
 */
@Controller
public class UEditorController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NewsService newsService;


    @RequestMapping("/editor")
    private String showPage(ModelMap map) {

        Subject subject = SecurityUtils.getSubject();
        List<String> cateList = new ArrayList<>();
        if(subject.hasRole("管理员")){
            List<Category> categoryList = categoryService.findAll();
            for(Category category : categoryList){
                cateList.add(category.getCategoryName());
            }
            map.addAttribute("news",new News());
            map.addAttribute("cateList",cateList);
            return "editor";
        }
        User user = (User)subject.getSession().getAttribute("user");
        System.out.println(user.getRoleId());
        Roles roles = roleService.findOneById(user.getRoleId());
        cateList.add(roles.getRoleName());
        map.addAttribute("news",new News());
        map.addAttribute("cateList",cateList);
        return "editor";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(HttpServletRequest request,
                                 ModelMap map){
        String cateName = request.getParameter("catename");
        String newsContent = request.getParameter("newsContent");
        String newsTitle = request.getParameter("newsTitle");
        Category category = categoryService.findByCategoryName(cateName);
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            News news = new News();
            news.setUserId(user.getId());
            System.out.println(user.getId());
            news.setAddTime(LocalDateTime.now());
            System.out.println(news.getAddTime());
            news.setCategoryId(category.getId());
            news.setNewsContent(newsContent);
            news.setNewsTitle(newsTitle);
            News news1 = newsService.saveOrUpdate(news);
            map.addAttribute("news",news1);
            return "redirect:/news/list";


    }

    @RequestMapping("/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/news");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
