package com.example.beanpost.dao.impl;

import com.example.beanpost.EmployeeDTO;
import com.example.beanpost.dao.EmployeeDAO;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDAOImpl implements EmployeeDAO, InitializingBean, DisposableBean {
    @Override
    public EmployeeDTO createNewEmployee() {
        EmployeeDTO e = new EmployeeDTO();
        e.setId(1);
        e.setFirstName("Lokesh");
        e.setLastName("Gupta");
        return e;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(" this class destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" this class afterPropertiesSet...");
    }
}