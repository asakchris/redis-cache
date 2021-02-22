package com.example.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

  private final CacheManager cacheManager;

  /**
   * Associate the specified value with the specified key in given cache
   *
   * @param cacheName Name of the cache
   * @param cacheKey the key with which the specified value is to be associated
   * @param value the value to be associated with the specified key
   */
  @Override
  public void addToCache(String cacheName, Object cacheKey, Object value) {
    cacheManager.getCache(cacheName).put(cacheKey, value);
  }

  /**
   * Associate the specified value with the specified key in given cache if it is not set already
   *
   * @param cacheName Name of the cache
   * @param cacheKey the key with which the specified value is to be associated
   * @param value the value to be associated with the specified key
   */
  @Override
  public void addToCacheIfAbsent(String cacheName, Object cacheKey, Object value) {
    cacheManager.getCache(cacheName).putIfAbsent(cacheKey, value);
  }

  /**
   * @param cacheName Name of the cache
   * @param cacheKey the key with which the specified value is associated
   * @param type the required type of the returned value
   * @param <T> the type of value in cache
   * @return the value to which this cache maps the specified key
   */
  @Override
  public <T> T getValueFromCache(String cacheName, Object cacheKey, Class<T> type) {
    return cacheManager.getCache(cacheName).get(cacheKey, type);
  }

  /**
   * It evicts the value from cache based on given key
   *
   * @param cacheName Name of the cache
   * @param cacheKey Key to be evicted
   * @return {@code true} if the cache was known to have a mapping for this key before, {@code
   *     false} if it did not
   */
  @Override
  public boolean evictCacheByKey(String cacheName, Object cacheKey) {
    return cacheManager.getCache(cacheName).evictIfPresent(cacheKey);
  }

  /**
   * It evicts all values from given cache
   *
   * @param cacheName Name of the cache
   * @return {@code true} if the cache had known mappings, {@code false} if it did not
   */
  @Override
  public boolean evictCacheAll(String cacheName) {
    return cacheManager.getCache(cacheName).invalidate();
  }
}
