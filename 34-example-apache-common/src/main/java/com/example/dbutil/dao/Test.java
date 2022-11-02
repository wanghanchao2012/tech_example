package com.example.dbutil.dao;

import com.example.dbutil.Employee;
import com.example.dbutil.dao.impl.EmpDaoImpl;

public class Test {
    public static void main(String[] args) {
        Employee zhang = new EmpDaoImpl().selectEmpByLoginAccount("zhang");
        System.out.println("姓名：" + zhang.getFirst() + zhang.getLast() + ",芳龄：" + zhang.getAge());
    }
}
