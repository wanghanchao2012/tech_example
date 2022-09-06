package com.example.shiro.auth;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.shiro.beans.Permissions;
import com.example.shiro.beans.Role;
import com.example.shiro.beans.User;
import com.example.shiro.service.LoginService;
import com.example.shiro.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserRealm extends AuthorizingRealm {
    @Autowired
    LoginService loginService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登陆用户

        String username = JWTUtil.getUsername(principalCollection.toString());
        User currentUser = loginService.getUserByName(username);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : currentUser.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permissions permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        String loginTime = JWTUtil.getLoginTime(token);
        
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = loginService.getUserByName(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        Exception verify = JWTUtil.verify(token, username, userBean.getPassword());
        if (Objects.nonNull(verify)) {
            if (verify instanceof TokenExpiredException) {
                throw new AuthenticationException("token expired!");
            } else if (verify instanceof SignatureVerificationException) {
                throw new AuthenticationException("username or password error");
            }
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}