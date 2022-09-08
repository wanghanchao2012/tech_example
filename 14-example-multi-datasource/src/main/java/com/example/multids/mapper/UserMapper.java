package com.example.multids.mapper;

import com.example.multids.entity.Money;
import com.example.multids.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryUser();

    List<Money> queryMoney();
}
