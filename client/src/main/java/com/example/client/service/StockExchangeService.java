package com.example.client.service;

import com.example.client.model.StockExchange;

public interface StockExchangeService {
  StockExchange createStockExchange(StockExchange stockExchange);

  StockExchange findStockExchangeById(String stockExchangeId);

  StockExchange deleteStockExchange(String stockExchangeId);
}
