package com.example.client.controller;

import com.example.client.model.Country;
import com.example.client.service.CountryService;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/countries")
@Slf4j
@RequiredArgsConstructor
public class CountryController {
  private final CountryService service;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Country createCountry(@RequestBody @Valid Country country) {
    return service.createCountry(country);
  }

  @GetMapping("/exists")
  @ResponseStatus(code = HttpStatus.OK)
  public Map<String, String> isCountryExist(@RequestParam String countryId) {
    final boolean countryExist = service.isCountryExist(countryId);
    if (countryExist) {
      return Map.of("message", "Country exists");
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid country id");
    }
  }
}
