package com.example.client.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {
  private final CacheProperties cacheProperties;

  @Bean
  public RedisCacheManagerBuilderCustomizer myRedisCacheManagerBuilderCustomizer() {
    return builder -> {
      final RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
      cacheProperties
          .getConfigByName()
          .forEach(
              (name, options) ->
                  builder.withCacheConfiguration(name, config.entryTtl(options.getTimeToLive())));
    };
  }
}
