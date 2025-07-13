package com.example.ormlearn.service;

import com.example.ormlearn.model.Skill;
import com.example.ormlearn.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public Skill get(int id) {
        LOGGER.info("Start get Skill");
        return skillRepository.findById(id).get();
    }

    @Transactional
    public void save(Skill skill) {
        LOGGER.info("Start save Skill");
        skillRepository.save(skill);
        LOGGER.info("End save Skill");
    }
}
