package com.mbo.news.entity;

import javax.persistence.*;

/**
 * @program: news
 * @description: 新闻类别实体类
 * @author: mbo
 * @create: 2018-12-18 08:37
 **/
@Entity
@Table(name = "t_category")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",nullable = false)
    private Integer id;
    @Column(name = "category_name",nullable = false,length = 20)
    private String categoryName;
    @Column(name = "deleted",length = 2,columnDefinition="tinyint default 0",nullable = false)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
