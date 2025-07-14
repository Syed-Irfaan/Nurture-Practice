package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:department.xml")  
public class EmployeeStaticListApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeStaticListApplication.class, args);
    }
}
