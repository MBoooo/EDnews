package com.mbo.news.config.shiro;

import com.mbo.news.entity.Roles;
import com.mbo.news.entity.User;
import com.mbo.news.service.RoleService;
import com.mbo.news.service.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authc.AuthenticationException;

import java.util.List;

/**
 * @program: news
 * @description:
 * @author: mbo
 * @create: 2018-12-19 15:03
 **/

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();
        List<User> userList = userService.getUserByName(username);
        User user = userList.get(0);
        Roles roles = roleService.findOneById(user.getRoleId());
        authorizationInfo.addRole(roles.getRoleName());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        List<User> userList = userService.getUserByName(username);
        User user = userList.get(0);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        return authenticationInfo;
    }

}
