package com.mbo.news.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @program: news
 * @description: 新闻实体类
 * @author: mbo
 * @create: 2018-12-18 08:46
 **/

@Entity
@Table(name = "t_news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "news_title",nullable = false,length = 50)
    private String newsTitle;
    @Lob
    @Column(name = "news_content",nullable = false,columnDefinition = "TEXT")
    private String newsContent;
    @Column(name = "news_clicks",nullable = false,length = 20)
    private int clicks;
    private LocalDateTime addTime;
    private Integer userId;
    private Integer categoryId;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
