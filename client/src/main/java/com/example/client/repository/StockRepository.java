package com.example.client.repository;

import com.example.client.model.Stock;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, String> {
  List<Stock> findAll();

  List<Stock> findByStockName(String stockName);
}
