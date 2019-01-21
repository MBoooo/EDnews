package com.mbo.news.service;

import com.mbo.news.entity.News;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-18 16:31
 **/

public interface NewsService {

    public News saveOrUpdate(News news);

    public List<News> findAll();

    public List<News> findAllByOrderByaddTime();

    public void deleteNews(Integer newsId);

    public News findOneById(Integer id);

    public News findByNewsTitle(String title);

    public List<News> findByCategoryId(Integer id);

    public News update(News news);
}
