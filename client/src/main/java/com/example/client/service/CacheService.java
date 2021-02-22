package com.example.client.service;

public interface CacheService {
  void addToCache(String cacheName, Object cacheKey, Object value);

  void addToCacheIfAbsent(String cacheName, Object cacheKey, Object value);

  <T> T getValueFromCache(String cacheName, Object cacheKey, Class<T> type);

  boolean evictCacheByKey(String cacheName, Object cacheKey);

  boolean evictCacheAll(String cacheName);
}
