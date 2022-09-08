package com.example.multids.dao;

import com.example.multids.dynamic.DataSourceType;
import com.example.multids.dynamic.DsScope;
import com.example.multids.entity.Money;
import com.example.multids.entity.User;
import com.example.multids.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    UserMapper userMapper;

    @DsScope(scope = "test")
    public void printMoney() {
        System.out.println("----------------used JdbcTemplate query Money--------------");
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper<Money>(Money.class);
        List<Money> monies = jdbcTemplate.query("select * from money", rowMapper);
        monies.forEach(record -> {
            System.out.println(record.toString());
        });
        System.out.println("----------------used MyBatis query Money--------------");
        List<Money> monies1 = userMapper.queryMoney();
        monies.forEach(record -> {
            System.out.println(record.toString());
        });

    }

    @DsScope(scope = "jwt")
    public void printUser() {
        System.out.println("----------------used JdbcTemplate query User--------------");
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query("select * from user", rowMapper);
        users.forEach(record -> {
            System.out.println(record.toString());
        });
        System.out.println("----------------used MyBatis query User--------------");
        List<User> monies1 = userMapper.queryUser();
        monies1.forEach(record -> {
            System.out.println(record.toString());
        });
    }


}
