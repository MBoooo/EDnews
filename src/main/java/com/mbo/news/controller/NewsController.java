package com.mbo.news.controller;

import com.mbo.news.entity.Category;
import com.mbo.news.entity.News;
import com.mbo.news.entity.Roles;
import com.mbo.news.entity.User;
import com.mbo.news.service.CategoryService;
import com.mbo.news.service.NewsService;
import com.mbo.news.service.RoleService;
import com.mbo.news.service.UserService;
import com.mbo.news.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-18 21:38
 **/

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping("/list")
    public String newsList(ModelMap map){
        Subject subject = SecurityUtils.getSubject();
        List<String> cateList = new ArrayList<>();
        List<String> userList = new ArrayList<>();
        if(subject.hasRole("管理员")){
            List<News> newsList = newsService.findAllByOrderByaddTime();
            map.addAttribute("newsList",newsList);
            for(News news : newsList){
                Category category = categoryService.findOneById(news.getCategoryId());
                cateList.add(category.getCategoryName());
                User user = userService.findOneById(news.getUserId());
                userList.add(user.getUserName());
            }
            map.addAttribute("cateList",cateList);
            map.addAttribute("userList",userList);
            return "news";
        }else {
            User user = (User) subject.getSession().getAttribute("user");
            Roles roles = roleService.findOneById(user.getRoleId());
            //新闻栏目和角色管理员名称相同
            Category category = categoryService.findByCategoryName(roles.getRoleName());
            List<News> newsList = newsService.findByCategoryId(category.getId());
            System.out.println(newsList.size());
            for(News news : newsList){
                cateList.add(category.getCategoryName());
                User us = userService.findOneById(news.getUserId());
                userList.add(us.getUserName());
            }
            map.addAttribute("newsList",newsList);
            map.addAttribute("cateList",cateList);
            map.addAttribute("userList",userList);
            return "news";
        }


    }

    @GetMapping("/getNews")
    public String getNews(HttpServletRequest request,
                          ModelMap map){
        String newsTitle = request.getParameter("newsTitle");
        String cateName = request.getParameter("cateName");
        List<String> cateList = new ArrayList<>();
        List<String> userList = new ArrayList<>();
        if(newsTitle != null && newsTitle != ""){
            News news = newsService.findByNewsTitle(newsTitle);
            User user = userService.findOneById(news.getUserId());
            Category category = categoryService.findOneById(news.getCategoryId());
            List<News> newsList = new ArrayList<>();
            newsList.add(news);
            cateList.add(category.getCategoryName());
            userList.add(user.getUserName());
            map.addAttribute("newsList",newsList);
            map.addAttribute("userList",userList);
            map.addAttribute("cateList",cateList);
            return "news";
        }else if(cateName != null && cateName != ""){
            Category category = categoryService.findByCategoryName(cateName);
            List<News> newsList = newsService.findByCategoryId(category.getId());
            for(News news : newsList){
                User user = userService.findOneById(news.getUserId());
                userList.add(user.getUserName());
                cateList.add(cateName);
            }
            map.addAttribute("newsList",newsList);
            map.addAttribute("userList",userList);
            map.addAttribute("cateList",cateList);
            return "news";
        }
        return "redirect:/news/list";
    }


    @GetMapping("/update/{newsId}")
    public String updateUser(@PathVariable Integer newsId,
                             ModelMap map){
        News news = newsService.findOneById(newsId);
        Category category = categoryService.findOneById(news.getCategoryId());
        List<String> cateList =  new ArrayList<>();
        cateList.add(category.getCategoryName());
        map.addAttribute("news",news);
        map.addAttribute("cateList",cateList);
        return "editor";
    }


    @GetMapping("/delete/{newsId}")
    public String deleUser(@PathVariable Integer newsId,
                           Model model){
//        if( userId == ""){
//            model.addAttribute("msg",ResponseUtil.serious());
//            return "error";
//        }
        newsService.deleteNews(newsId);
        return "redirect:/news/list";
    }

}
