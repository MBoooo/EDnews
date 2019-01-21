package com.mbo.news.dao;

import com.mbo.news.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-21 18:45
 **/

public interface RoleJpaDao extends JpaRepository<Roles,Integer> {
    Roles findOneById(Integer id);
    Roles findByRoleName(String name);
}
