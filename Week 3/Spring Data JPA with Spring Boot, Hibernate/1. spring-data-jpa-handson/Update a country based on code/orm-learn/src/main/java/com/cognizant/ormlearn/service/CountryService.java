package com.cognizant.ormlearn.service;

import java.util.List;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import jakarta.transaction.Transactional;

public interface CountryService {
    List<Country> getAllCountries();
    
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException;
    
    @Transactional
    Country findCountryByCode(String code) throws CountryNotFoundException;

    void addCountry(Country country);

    void deleteCountry(String code);

    List<Country> findByNameContaining(String partialName);
}
