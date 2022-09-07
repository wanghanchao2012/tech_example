package com.example.aop;

import com.example.beanpost.dao.impl.EmployeeDAOImpl;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

    @Resource
    EmployeeDAOImpl employeeDAO;

    @Test
    public void start() {
        employeeDAO.createNewEmployee();
    }
}
