package com.example.client.service;

import com.example.client.model.Country;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private static final String CACHE_NAME = "countries";

  private final CacheService cacheService;

  @Override
  @CachePut(value = CACHE_NAME, key = "#country.countryId")
  public Country createCountry(Country country) {
    return country;
  }

  @Override
  @Cacheable(value = "country.exist", key = "#countryId")
  public boolean isCountryExist(String countryId) {
    return Optional.ofNullable(cacheService.getValueFromCache(CACHE_NAME, countryId, Country.class))
        .isPresent();
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
