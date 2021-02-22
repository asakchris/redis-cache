package com.example.client.service;

import com.example.client.model.Country;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

  @Override
  @CachePut(value = "countries", key = "#country.countryId")
  public Country createCountry(Country country) {
    return country;
  }

  @Override
  @Cacheable(value = "countries", key = "#countryId")
  public Country findCountryById(String countryId) {
    return null;
  }

  @Override
  public Country deleteCountry(String countryId) {
    return null;
  }
}
