package com.mbo.news.service.serviceimp;

import com.mbo.news.dao.NewsJpaDao;
import com.mbo.news.entity.News;
import com.mbo.news.entity.User;
import com.mbo.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-18 16:34
 **/
@Service
public class NewsServiceImp implements NewsService {

    @Autowired
    private NewsJpaDao newsJpaDao;



    /**
    * 增加新闻
    * @Param:
    * @return:
    * @Author: mbo
    * @Date: 2018/12/18
    */

    @Override
    public News saveOrUpdate(News news) {
            return newsJpaDao.saveAndFlush(news);
    }

    @Override
    public void deleteNews(Integer newsId) {
        newsJpaDao.deleteById(newsId);
    }

    @Override
    public List<News> findAll() {
        return newsJpaDao.findAll();
    }

    @Override
    public News findOneById(Integer id) {
        return newsJpaDao.findOneById(id);
    }

    @Override
    public List<News> findAllByOrderByaddTime() {
        return newsJpaDao.findAllByOrderByAddTime();
    }

    @Override
    public News findByNewsTitle(String title) {
        return newsJpaDao.findByNewsTitle(title);
    }

    @Override
    public List<News> findByCategoryId(Integer id) {
        return newsJpaDao.findByCategoryId(id);
    }

    @Override
    public News update(News news) {
        News news1 = newsJpaDao.findOneById(news.getId());
        news1.setNewsTitle(news.getNewsTitle());
        news1.setNewsContent(news.getNewsContent());
        news1.setClicks(news.getClicks());
        news1.setCategoryId(news.getCategoryId());
        news1.setUserId(news.getUserId());
        newsJpaDao.saveAndFlush(news1);
        return news1;
    }
}
