package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);

        try {
        	testDeleteCountry();
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
        }
    }

    private static void getCountryTest() throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End");
    }
    
    private static void testAddCountry() throws Exception {
        LOGGER.info("Start - testAddCountry");

        Country country = new Country();
        country.setCode("ZZ");
        country.setName("Zootopia");

        countryService.addCountry(country);

        Country retrievedCountry = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Added Country: {}", retrievedCountry);

        LOGGER.info("End - testAddCountry");
    }
    
    private static void testUpdateCountry() throws CountryNotFoundException {
        LOGGER.info("Start - testUpdateCountry");

        countryService.updateCountry("IN", "Bharat");

        Country updatedCountry = countryService.findCountryByCode("IN");
        LOGGER.debug("Updated Country: {}", updatedCountry);

        LOGGER.info("End - testUpdateCountry");
    }
    
    private static void testDeleteCountry() {
        LOGGER.info("Start");
        try {
            countryService.deleteCountry("ZZ"); 
            LOGGER.debug("Deleted country with code ZZ");
        } catch (CountryNotFoundException e) {
            LOGGER.error("Error: {}", e.getMessage());
        }
        LOGGER.info("End");
    }



}
