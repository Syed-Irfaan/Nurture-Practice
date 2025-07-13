package com.example.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ormlearn.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}