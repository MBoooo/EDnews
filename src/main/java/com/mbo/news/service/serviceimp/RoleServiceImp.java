package com.mbo.news.service.serviceimp;

import com.mbo.news.dao.RoleJpaDao;
import com.mbo.news.entity.Roles;
import com.mbo.news.service.RoleService;
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
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleJpaDao roleJpaDao;

    @Override
    public List findAll() {
        return roleJpaDao.findAll();
    }


    @Override
    public Roles creatrOrUpdate(Roles roles) {
        return roleJpaDao.saveAndFlush(roles);
    }

    @Override
    public void delete(Integer id) {
        roleJpaDao.deleteById(id);
    }

    @Override
    public Roles findOneById(Integer id) {
        return roleJpaDao.findOneById(id);
    }

    @Override
    public Roles findByRoleName(String name) {
        return roleJpaDao.findByRoleName(name);
    }
}
