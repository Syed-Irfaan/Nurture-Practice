package com.cognizant.ormlearn;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    private final CountryRepository countryRepository;

    public OrmLearnApplication(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Search: Name contains 'ou' ===");
        List<Country> list1 = countryRepository.findByNameContaining("ou");
        list1.forEach(c -> System.out.println(c.getCode() + " - " + c.getName()));

        System.out.println("\n=== Search: Name contains 'ou' and sorted ===");
        List<Country> list2 = countryRepository.findByNameContainingOrderByNameAsc("ou");
        list2.forEach(c -> System.out.println(c.getCode() + " - " + c.getName()));

        System.out.println("\n=== Search: Name starts with 'Z' ===");
        List<Country> list3 = countryRepository.findByNameStartingWith("Z");
        list3.forEach(c -> System.out.println(c.getCode() + " - " + c.getName()));
    }
}
