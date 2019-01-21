package com.mbo.news.service.serviceimp;

import com.mbo.news.dao.UserJpaDao;
import com.mbo.news.entity.User;
import com.mbo.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: news
 * @description: 用户服务实现类
 * @author: mbo
 * @create: 2018-12-18 15:41
 **/
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserJpaDao userJpaDao;


    @Override
    public List<User> getUserByName(String username) {
        return userJpaDao.findByUserName(username);
    }

    @Override
    public User saveOrUpdate(User user){
        return userJpaDao.saveAndFlush(user);

    }

    @Override
    public void deleteUser(Integer userId) {
        userJpaDao.deleteById(userId);
    }

    @Override
    public User findOneById(Integer id) {
        return userJpaDao.findOneById(id);
    }

    @Override
    public List<User> findAll() {
        return userJpaDao.findAll();
    }

    @Override
    public List<User> findByRoleId(Integer roleId) {
        return userJpaDao.findByRoleId(roleId);
    }
}
