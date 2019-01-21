package com.mbo.news.service;

import com.mbo.news.entity.Category;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-22 20:34
 **/

public interface CategoryService {
    public List findAll();

    public void delete(Integer id);

    public Category createOrUpdate(Category category);

    public Category findOneById(Integer id);

    public Category findByCategoryName(String name);
}
