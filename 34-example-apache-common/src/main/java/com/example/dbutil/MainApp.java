package com.example.dbutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {


    public static void main(String[] args) throws SQLException {
        Employee emp = DbHelper.query("SELECT * FROM employees WHERE first=?", Employee.class, "Sumit");
        System.out.print(", Age: " + emp.getAge());
        System.out.print(", First: " + emp.getFirst());
        System.out.println(", Last: " + emp.getLast());
        // DbHelper.upsert("INSERT INTO employees(id,age,first,last) VALUES (?,?,?,?)", 104, 30, "Sohan", "Kumar");
        List<Employee> list = DbHelper.list("SELECT * FROM employees", Employee.class);
        list.forEach(record -> {
            System.out.println(record.toString());
        });
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{2001, 21, "zhang", "san"});
        params.add(new Object[]{2002, 21, "li", "si"});
        DbHelper.updateBatch("INSERT INTO employees(id,age,first,last) VALUES (?,?,?,?)", params);
    }
}