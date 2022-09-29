package com.example;

import com.example.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        String resource = "conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "com.example.mapper.UserMapper.getUser";
        User user = new User();
        user.setEnabled("1");
        user.setUsername("admin");
        List<Object> list = session.selectList(statement, user);
        list.forEach(e -> {
            System.out.println(e);
        });
        delete(session);
        update(session);
        insert(session);
    }

    public static void delete(SqlSession session) {
        String statementdl = "com.example.mapper.UserMapper.deleteUser";
        try {
            session.delete(statementdl, "whc");
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    public static void update(SqlSession session) {
        String statementup = "com.example.mapper.UserMapper.updateUser";
        User userup = new User("admin", "password", "0");
        try {
            session.update(statementup, userup);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }


    public static void insert(SqlSession session) {
        int num = (int) (Math.random() * 1000 - 1);
        String statementIn = "com.example.mapper.UserMapper.insertUser";
        User userup = new User("whc", "password", String.valueOf(num));
        try {
            session.insert(statementIn, userup);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }
}