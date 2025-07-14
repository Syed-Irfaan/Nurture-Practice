package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional
    public Country findCountryByCode(String code) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country with code " + code + " not found.");
        }
        return result.get();
    }

    @Override
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Optional<Country> optionalCountry = countryRepository.findById(code);
        if (!optionalCountry.isPresent()) {
            throw new CountryNotFoundException("Country not found with code: " + code);
        }

        Country country = optionalCountry.get();
        country.setName(name); 
        countryRepository.save(country); 
    }


    
    @Override
    @Transactional
    public void deleteCountry(String code) throws CountryNotFoundException {
        Optional<Country> country = countryRepository.findById(code);
        if (!country.isPresent()) {
            throw new CountryNotFoundException("Country not found with code: " + code);
        }
        countryRepository.deleteById(code);
    }

    @Override
    public List<Country> findByNameContaining(String partialName) {
        return countryRepository.findByNameContainingIgnoreCase(partialName);
    }
}
