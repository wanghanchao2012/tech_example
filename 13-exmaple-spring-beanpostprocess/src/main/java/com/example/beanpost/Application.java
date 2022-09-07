package com.example.beanpost;

import com.example.beanpost.dao.impl.EmployeeDAOImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    EmployeeDAOImpl employeeDAO;

    @Override
    public void run(String... args) throws Exception {
        employeeDAO.createNewEmployee();
    }
}   
