package com.mbo.news.dao;

import com.mbo.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: news
 * @description: 新闻数据访问接口
 * @author: mbo
 * @create: 2018-12-18 09:14
 **/

public interface NewsJpaDao extends JpaRepository<News,Integer> {
    News findOneById(Integer id);

    List<News> findAllByOrderByAddTime();

    News findByNewsTitle(String title);

    List<News> findByCategoryId(Integer id);
}
