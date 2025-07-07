package com.example.country.service;
import com.example.country.entity.Country;
import com.example.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country findByCode(String code) {
        return countryRepository.findById(code).orElse(null);
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(String code, Country updatedCountry) {
        Country existing = findByCode(code);
        if (existing != null) {
            existing.setCoName(updatedCountry.getCoName());
            return countryRepository.save(existing);
        }
        return null;
    }

    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    public List<Country> searchCountriesByName(String partialName) {
        return countryRepository.findByCoNameContainingIgnoreCase(partialName);
    }
}