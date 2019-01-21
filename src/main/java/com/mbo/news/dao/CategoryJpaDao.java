package com.mbo.news.dao;

import com.mbo.news.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * @program: news
 * @description: 新闻类别数据访问接口
 * @author: mbo
 * @create: 2018-12-18 09:33
 **/

public interface CategoryJpaDao extends JpaRepository<Category,Integer> {

    Category findByCategoryName(String name);

    Category findOneById(Integer id);

}
