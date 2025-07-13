package com.example;

import com.example.model.Employee;
import com.example.model.Skill;
import com.example.service.EmployeeService;
import com.example.service.TestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeHqlDemoApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeHqlDemoApplication.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TestDataService testDataService; 

    public static void main(String[] args) {
        SpringApplication.run(EmployeeHqlDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testDataService.insertTestData(); 
      //  testGetAllPermanentEmployees();
     //  testGetAverageSalary();
        testGetAllEmployeesNative();


    }

    public void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        var employees = employeeService.getAllPermanentEmployees();
        for (Employee e : employees) {
            LOGGER.debug("Employee: {}", e.getName());
            LOGGER.debug("Skills: {}", e.getSkillList().stream().map(Skill::getName).toList());
        }
        LOGGER.info("End");
    }  
        
        public void testGetAverageSalary() {
            LOGGER.info("Start - Average Salary");
            double avg = employeeService.getAverageSalary(1); 
            LOGGER.debug("Average Salary for Department ID 1: {}", avg);
            LOGGER.info("End - Average Salary");
        }
        
        private void testGetAllEmployeesNative() {
            LOGGER.info("Start - Get All Employees Using Native Query");
            var employees = employeeService.getAllEmployeesNative();
            employees.forEach(e -> LOGGER.debug("Employee: {}", e.getName()));
            LOGGER.info("End - Get All Employees Using Native Query");
        }

}
