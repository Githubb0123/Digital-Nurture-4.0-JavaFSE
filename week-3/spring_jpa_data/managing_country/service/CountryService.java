package com.example.country.service;
import com.example.country.entity.Country;
import java.util.List;

public interface CountryService {
    Country findByCode(String code);
    Country addCountry(Country country);
    Country updateCountry(String code, Country updatedCountry);
    void deleteCountry(String code);
    List<Country> searchCountriesByName(String partialName);
}