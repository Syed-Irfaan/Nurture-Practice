package com.example.ormlearn.service;

import com.example.ormlearn.model.Department;
import com.example.ormlearn.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Department get(int id) {
        LOGGER.info("Start get Department");
        return departmentRepository.findById(id).get();
    }

    @Transactional
    public void save(Department department) {
        LOGGER.info("Start save Department");
        departmentRepository.save(department);
        LOGGER.info("End save Department");
    }
}
