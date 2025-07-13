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
        testDataService.insertTestData(); // ✅ Proper transactional insert now
        testGetAllPermanentEmployees();
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
}
