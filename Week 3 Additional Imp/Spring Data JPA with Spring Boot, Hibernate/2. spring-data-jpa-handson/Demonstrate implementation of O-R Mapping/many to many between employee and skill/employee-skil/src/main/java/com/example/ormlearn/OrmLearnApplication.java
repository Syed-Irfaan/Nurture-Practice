
package com.example.ormlearn;

import com.example.ormlearn.model.Department;
import com.example.ormlearn.model.Employee;
import com.example.ormlearn.model.Skill;
import com.example.ormlearn.service.DepartmentService;
import com.example.ormlearn.service.EmployeeService;
import com.example.ormlearn.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OrmLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SkillService skillService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            insertInitialData();
            // testGetEmployee();
            testAddSkillToEmployee();
        };
    }

    private void insertInitialData() {
        LOGGER.info("Inserting initial data...");

        Department dept = new Department();
        dept.setName("Engineering");
        departmentService.save(dept);

        Employee emp = new Employee();
        emp.setName("John Doe");
        emp.setSalary(60000);
        emp.setPermanent(true);
        emp.setDateOfBirth(Date.valueOf("1990-01-01"));
        emp.setDepartment(dept);
        employeeService.save(emp);

        Skill skill = new Skill();
        skill.setName("Java");
        skillService.save(skill);

        Skill skill2 = new Skill();
        skill2.setName("Spring Boot");
        skillService.save(skill2);

        LOGGER.info("Initial data inserted.");
    }

    private void testGetEmployee() {
        LOGGER.info("Start testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        LOGGER.debug("Skills: {}", employee.getSkillList());
        LOGGER.info("End testGetEmployee");
    }

    private void testAddSkillToEmployee() {
        LOGGER.info("Start testAddSkillToEmployee");

        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(1);

        Set<Skill> skillList = employee.getSkillList();
        if (skillList == null) {
            skillList = new HashSet<>();
        }

        skillList.add(skill);
        employee.setSkillList(skillList);

        employeeService.save(employee);

        LOGGER.debug("Updated Employee with Skill: {}", employee.getSkillList());
        LOGGER.info("End testAddSkillToEmployee");
    }
}
