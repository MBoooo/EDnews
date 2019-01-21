package com.mbo.news.entity;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: news
 * @description: 用户实体类
 * @author: mbo
 * @create: 2018-12-17 22:19
 **/

@Entity
@Table(name = "t_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", nullable = false, length = 20)
    private String userName;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    private LocalDateTime addTime;
    private Integer roleId;


    public User(){

    }
    public User(String userName,String password ){
        this.userName = userName;
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
}
