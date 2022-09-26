/*
package com.example.service;


import com.example.dto.entity.Authorities;
import com.example.dto.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过业务方法获取用户及权限信息
        List<Users> query = namedParameterJdbcTemplate.query("select * from users where username = '" + username + "' ", new BeanPropertyRowMapper<>(Users.class));
        if (!CollectionUtils.isEmpty(query)) {
            Users users = query.get(0);
            List<Authorities> authorities = namedParameterJdbcTemplate.query("select * from authorities where username = '" + username + "' ", new BeanPropertyRowMapper<>(Authorities.class));
            // 对用户权限进行封装
            List<SimpleGrantedAuthority> list = authorities.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
            // 返回封装的UserDetails用户详情类
            if (users != null) {
                UserDetails userDetails = new User(users.getUsername(), users.getPassword(), list);
                return userDetails;
            }
        } else {
            // 如果查询的用户不存在（用户名不存在），必须抛出此异常
            throw new UsernameNotFoundException("当前用户不存在！");
        }
        return null;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }
}*/
