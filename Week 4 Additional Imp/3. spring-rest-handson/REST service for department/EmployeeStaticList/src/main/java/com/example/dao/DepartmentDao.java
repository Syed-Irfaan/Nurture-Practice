package com.example.dao;

import com.example.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao {

    private final List<Department> departmentList;

    @Autowired
    public DepartmentDao(@Qualifier("departmentList") List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Department> getAllDepartments() {
        return departmentList;
    }
}
