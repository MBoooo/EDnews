package com.mbo.news.entity;

import javax.persistence.*;

/**
 * @program: news
 * @description: 角色实体类
 * @author: mbo
 * @create: 2018-12-20 14:00
 **/
@Entity
@Table(name = "t_roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "roles_name",nullable = false,length = 20)
    private String roleName;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }


}
