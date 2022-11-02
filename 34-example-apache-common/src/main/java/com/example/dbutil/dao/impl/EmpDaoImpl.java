package com.example.dbutil.dao.impl;

import com.example.dbutil.dao.BaseDao;
import com.example.dbutil.Employee;
import com.example.dbutil.dao.api.EmpDao;

public class EmpDaoImpl extends BaseDao<Employee> implements EmpDao {
    @Override
    public Employee selectEmpByLoginAccount(String loginAccount) {

        // 1、编写 SQL 语句
        String sql = "select * from employees where first=?";

        // 2、调用父类方法查询单个对象
        return super.getSingleBean(sql, Employee.class, loginAccount);
    }
}
