package com.example;

import com.example.dao.EmployeeDao;
import com.example.model.Employee;

public class MainApp {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        for (Employee emp : dao.getAllEmployees()) {
            System.out.println(emp);
        }
    }
}
