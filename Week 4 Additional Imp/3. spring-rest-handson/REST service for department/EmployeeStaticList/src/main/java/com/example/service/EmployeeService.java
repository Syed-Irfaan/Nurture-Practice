package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Employee;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Alice", "HR", Arrays.asList("Java", "Spring")));
        employees.add(new Employee(102, "Bob", "Finance", Arrays.asList("Excel", "Accounting")));
        employees.add(new Employee(103, "Charlie", "IT", Arrays.asList("Python", "SQL")));
        employees.add(new Employee(104, "Diana", "Marketing", Arrays.asList("SEO", "Content Writing")));
        return employees;
    }
}
