package com.mbo.news.service.serviceimp;

import com.mbo.news.dao.CategoryJpaDao;
import com.mbo.news.entity.Category;
import com.mbo.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-22 20:35
 **/
@Service
public class CateServiceImp implements CategoryService {

    @Autowired
    private CategoryJpaDao categoryJpaDao;

    @Override
    public List findAll() {
        return categoryJpaDao.findAll();
    }

    @Override
    public void delete(Integer id) {
        categoryJpaDao.deleteById(id);
    }

    @Override
    public Category createOrUpdate(Category category) {
        return categoryJpaDao.saveAndFlush(category);
    }

    @Override
    public Category findOneById(Integer id) {
        return categoryJpaDao.findOneById(id);
    }

    @Override
    public Category findByCategoryName(String name) {
        return categoryJpaDao.findByCategoryName(name);
    }
}
