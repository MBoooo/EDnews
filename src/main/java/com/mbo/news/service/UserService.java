package com.mbo.news.service;

import com.mbo.news.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-18 13:02
 **/

public interface UserService {

    public List<User> findAll();

    public List<User> getUserByName(String username);

    public User saveOrUpdate(User user);

    public void deleteUser(Integer userId);

    public User findOneById(Integer id);

    public List<User> findByRoleId(Integer roleId);



}
