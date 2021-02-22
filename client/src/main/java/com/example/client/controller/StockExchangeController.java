package com.example.client.controller;

import com.example.client.model.StockExchange;
import com.example.client.service.StockExchangeService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stock-exchanges")
@Slf4j
@RequiredArgsConstructor
public class StockExchangeController {
  private final StockExchangeService service;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public StockExchange createStockExchange(@RequestBody @Valid StockExchange stockExchange) {
    return service.createStockExchange(stockExchange);
  }

  @GetMapping(value = "/{stockExchangeId}")
  @ResponseStatus(code = HttpStatus.OK)
  public StockExchange findStockExchangeById(@PathVariable String stockExchangeId) {
    return service.findStockExchangeById(stockExchangeId);
  }

  @DeleteMapping(value = "/{stockExchangeId}")
  @ResponseStatus(code = HttpStatus.OK)
  public StockExchange deleteStockExchange(@PathVariable String stockExchangeId) {
    return service.deleteStockExchange(stockExchangeId);
  }
}
