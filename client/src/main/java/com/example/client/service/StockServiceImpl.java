package com.example.client.service;

import com.example.client.model.Stock;
import com.example.client.repository.StockRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
  private final StockRepository repository;

  @Override
  public Stock createStock(Stock stock) {
    final Stock save = repository.save(stock);
    log.info("After creating stock: {}", save);
    return save;
  }

  @Override
  public Stock findStockById(String stockId) {
    return repository
        .findById(stockId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid stock id"));
  }

  @Override
  public List<Stock> findAll() {
    return repository.findAll();
  }

  @Override
  public Stock deleteStock(String stockId) {
    final Optional<Stock> stockOptional = repository.findById(stockId);
    final Stock stock =
        stockOptional.orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid stock id"));
    repository.deleteById(stockId);
    return stock;
  }
}
