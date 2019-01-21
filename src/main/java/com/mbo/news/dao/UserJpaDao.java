package com.mbo.news.dao;

import com.mbo.news.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * @program: news
 * @description: 用户数据访问接口
 * @author: mbo
 * @create: 2018-12-17 22:26
 **/

public interface UserJpaDao extends JpaRepository<User,Integer> {
    List<User> findByUserName(String userName);

    User findOneById(Integer id);

    List<User> findByRoleId(Integer roleId);
}
