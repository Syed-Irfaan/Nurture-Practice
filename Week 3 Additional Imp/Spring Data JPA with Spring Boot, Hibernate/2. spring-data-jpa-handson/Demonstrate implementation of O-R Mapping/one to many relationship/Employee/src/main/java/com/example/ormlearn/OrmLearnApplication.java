package com.example.ormlearn;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ormlearn.model.Employee;
import com.example.ormlearn.model.Department;
import com.example.ormlearn.service.EmployeeService;
import com.example.ormlearn.service.DepartmentService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            insertInitialData();
          //  testGetEmployee();
          //  testAddEmployee();
          //  testUpdateEmployee();
            testGetDepartment();

        };
    }

    private void insertInitialData() {
        LOGGER.info("Inserting initial data...");

        Department dept1 = new Department();
        dept1.setName("Engineering");
        departmentService.save(dept1);

        Department dept2 = new Department();
        dept2.setName("HR");
        departmentService.save(dept2);

        Employee emp1 = new Employee();
        emp1.setName("John Doe");
        emp1.setSalary(60000);
        emp1.setPermanent(true);
        emp1.setDateOfBirth(Date.valueOf("1990-01-15"));
        emp1.setDepartment(dept1);
        employeeService.save(emp1);

        LOGGER.info("Initial data inserted.");
    }

    private void testGetEmployee() {
        LOGGER.info("Start testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        LOGGER.info("End testGetEmployee");
    }

    private void testAddEmployee() {
        LOGGER.info("Start testAddEmployee");

        Employee employee = new Employee();
        employee.setName("Zara Khan");
        employee.setSalary(55000);
        employee.setPermanent(true);
        employee.setDateOfBirth(Date.valueOf("1996-03-20"));

        Department dept = departmentService.get(1); // Engineering
        employee.setDepartment(dept);

        employeeService.save(employee);
        LOGGER.debug("Added Employee: {}", employee);

        LOGGER.info("End testAddEmployee");
    }

    private void testUpdateEmployee() {
        LOGGER.info("Start testUpdateEmployee");

        Employee employee = employeeService.get(1); // John Doe
        Department dept = departmentService.get(2); // HR

        employee.setDepartment(dept);
        employeeService.save(employee);

        LOGGER.debug("Updated Employee: {}", employee);
        LOGGER.info("End testUpdateEmployee");
    }
    
    private void testGetDepartment() {
        LOGGER.info("Start testGetDepartment");

        Department dept = departmentService.get(1); // Engineering
        LOGGER.debug("Department: {}", dept);
        LOGGER.debug("Employees in Department:");

        for (Employee e : dept.getEmployeeList()) {
            LOGGER.debug("-> {}", e);
        }


        LOGGER.info("End testGetDepartment");
       
    }

}
