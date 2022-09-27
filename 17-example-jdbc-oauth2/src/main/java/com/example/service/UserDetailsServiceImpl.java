package com.example.service;

import com.example.dto.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Users> query = namedParameterJdbcTemplate.query("select * from users where username = '" + username + "' ", new BeanPropertyRowMapper
                <>(Users.class));

        List<UserDetails> users = new ArrayList<>();
        Users record = query.get(0);
        /**
         * 利用passwordEncoder().encode(record.getPassword())来生成密码密闻存于数据库
         */
        String password = record.getPassword();
        UserDetails userDetails = User.withUsername(record.getUsername()).password(password).authorities("ROLE_ADMIN").build();
        users.add(userDetails);
        return userDetails;
    }
}