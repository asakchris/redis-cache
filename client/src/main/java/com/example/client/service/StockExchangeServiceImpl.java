package com.example.client.service;

import com.example.client.model.StockExchange;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockExchangeServiceImpl implements StockExchangeService {
  private static final String CACHE_NAME = "exchanges";

  private final CacheService cacheService;

  @Override
  public StockExchange createStockExchange(StockExchange stockExchange) {
    cacheService.addToCache(CACHE_NAME, stockExchange.getStockExchangeId(), stockExchange);
    return stockExchange;
  }

  @Override
  public StockExchange findStockExchangeById(String stockExchangeId) {
    log.info("stockExchangeId: {}", stockExchangeId);
    return cacheService.getValueFromCache(CACHE_NAME, stockExchangeId, StockExchange.class);
  }

  @Override
  public StockExchange deleteStockExchange(String stockExchangeId) {
    log.info("stockExchangeId: {}", stockExchangeId);
    final StockExchange stockExchange =
        Optional.ofNullable(findStockExchangeById(stockExchangeId))
            .orElseThrow(
                () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid stock exchange id"));
    cacheService.evictCacheByKey(CACHE_NAME, stockExchangeId);
    return stockExchange;
  }
}
