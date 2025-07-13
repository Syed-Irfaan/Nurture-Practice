package com.example.service;

import com.example.model.Department;
import com.example.model.Employee;
import com.example.model.Skill;
import com.example.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class TestDataService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void insertTestData() throws Exception {
        Department d1 = new Department(); d1.setName("IT");
        Department d2 = new Department(); d2.setName("HR");

        entityManager.persist(d1);
        entityManager.persist(d2);

        Skill s1 = new Skill(); s1.setName("Java");
        Skill s2 = new Skill(); s2.setName("Spring");

        entityManager.persist(s1);
        entityManager.persist(s2);

        Employee e1 = new Employee();
        e1.setName("Alice");
        e1.setSalary(50000);
        e1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"));
        e1.setPermanent(true);
        e1.setDepartment(d1);
        e1.setSkillList(Arrays.asList(s1, s2));

        Employee e2 = new Employee();
        e2.setName("Bob");
        e2.setSalary(40000);
        e2.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1992-02-02"));
        e2.setPermanent(false);
        e2.setDepartment(d2);
        e2.setSkillList(Arrays.asList(s2));

        employeeRepository.saveAll(Arrays.asList(e1, e2));
    }
}
