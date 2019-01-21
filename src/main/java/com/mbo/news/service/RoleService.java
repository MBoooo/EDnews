package com.mbo.news.service;

import com.mbo.news.entity.Roles;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-21 18:47
 **/

public interface RoleService {
    public List<Roles> findAll();

    public void delete(Integer id);

    public Roles findOneById(Integer id);

    public Roles creatrOrUpdate(Roles roles);

    public Roles findByRoleName(String name);
}
