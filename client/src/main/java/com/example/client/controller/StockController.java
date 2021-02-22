package com.example.client.controller;

import com.example.client.model.Stock;
import com.example.client.service.StockService;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping(value = "/stocks")
@Slf4j
public class StockController {

  private final StockService service;

  public StockController(StockService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Stock createStock(@RequestBody @Valid Stock stock) {
    return service.createStock(stock);
  }

  @GetMapping(value = "/{stockId}")
  @ResponseStatus(code = HttpStatus.OK)
  public Stock findStockById(@PathVariable String stockId) {
    return service.findStockById(stockId);
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<Stock> findAllStocks() {
    return service.findAll();
  }

  @DeleteMapping(value = "/{stockId}")
  @ResponseStatus(code = HttpStatus.OK)
  public Stock deleteStock(@PathVariable String stockId) {
    return service.deleteStock(stockId);
  }
}
