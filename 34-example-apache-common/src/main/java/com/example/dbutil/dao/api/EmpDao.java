package com.example.dbutil.dao.api;


import com.example.dbutil.Employee;

public interface EmpDao {
    Employee selectEmpByLoginAccount(String loginAccount);
}
