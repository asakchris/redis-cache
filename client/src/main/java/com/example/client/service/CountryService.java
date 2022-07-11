package com.example.client.service;

import com.example.client.model.Country;

public interface CountryService {
  Country createCountry(Country country);

  Country findCountryById(String countryId);

  Country deleteCountry(String countryId);

  boolean isCountryExist(String countryId);
}
