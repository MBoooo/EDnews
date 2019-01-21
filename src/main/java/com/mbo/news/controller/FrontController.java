package com.mbo.news.controller;

import com.mbo.news.entity.Category;
import com.mbo.news.entity.News;
import com.mbo.news.entity.User;
import com.mbo.news.service.CategoryService;
import com.mbo.news.service.NewsService;
import com.mbo.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-24 20:41
 **/

@Controller
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(ModelMap map){
        List<Category> categoryList = categoryService.findAll();
        List<Integer> newsNumList = new ArrayList<>();
        Map<Integer,List<News>> newsMap = new HashMap<>();
        for(Integer i = 0;i < categoryList.size();i++){
            Category category = categoryList.get(i);
            List<News> newsList = newsService.findByCategoryId(category.getId());
            newsMap.put(i,newsList);
            newsNumList.add(newsList.size());
        }
        map.addAttribute("cateList",categoryList);
//        map.addAttribute("newsList",newsList);
        map.addAttribute("newsNumList",newsNumList);
        map.addAttribute("newsMap",newsMap);
        return "index";
    }

    @RequestMapping("/getNews/{newsId}")
    public String getNews(@PathVariable Integer newsId,ModelMap map){
        News news = newsService.findOneById(newsId);
        news.setClicks(news.getClicks()+1);
        News news1 = newsService.update(news);
        User user = userService.findOneById(news.getUserId());
        map.addAttribute("news",news1);
        map.addAttribute("user",user);
        return "showNews";

    }


}
