package com.cognizant.ormlearn.service;

import java.util.List;
import com.cognizant.ormlearn.model.Country;

public interface CountryService {
    List<Country> getAllCountries();
    Country findCountryByCode(String code);
    void addCountry(Country country);
    void updateCountry(String code, String name);
    void deleteCountry(String code);
    List<Country> findByNameContaining(String partialName);
}
