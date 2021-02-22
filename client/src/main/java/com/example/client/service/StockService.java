package com.example.client.service;

import com.example.client.model.Stock;
import java.util.List;

public interface StockService {
  Stock createStock(Stock stock);

  Stock findStockById(String stockId);

  List<Stock> findAll();

  Stock deleteStock(String stockId);
}
