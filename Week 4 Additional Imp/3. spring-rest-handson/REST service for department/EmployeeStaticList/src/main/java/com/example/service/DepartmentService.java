package com.example.service;

import com.example.dao.DepartmentDao;
import com.example.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public List<Department> getAllDepartments() {
        System.out.println("DepartmentService: getAllDepartments() called");
        return departmentDao.getAllDepartments();
    }
}
