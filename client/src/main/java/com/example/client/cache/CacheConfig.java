package com.example.client.cache;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CacheConfig {
  private final CacheProperties cacheProperties;

  @Bean
  @Profile("local")
  public LettuceConnectionFactory localLettuceConnectionFactory() {
    if (log.isInfoEnabled()) {
      log.info(
          "Connecting to local redis server at {}:{}",
          cacheProperties.getHost(),
          cacheProperties.getPort());
    }
    RedisStandaloneConfiguration standaloneConfiguration =
        new RedisStandaloneConfiguration(cacheProperties.getHost(), cacheProperties.getPort());
    return new LettuceConnectionFactory(standaloneConfiguration);
  }

  @Bean
  @Profile("!local")
  public LettuceConnectionFactory lettuceConnectionFactory() {
    if (log.isInfoEnabled()) {
      log.info(
          "Connecting to AWS redis cluster at {}:{}",
          cacheProperties.getHost(),
          cacheProperties.getPort());
    }
    final List<String> nodes =
        Collections.singletonList(cacheProperties.getHost() + ":" + cacheProperties.getPort());
    RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(nodes);

    ClusterTopologyRefreshOptions refreshOptions =
        ClusterTopologyRefreshOptions.builder()
            .closeStaleConnections(true)
            .enableAllAdaptiveRefreshTriggers()
            .build();

    ClusterClientOptions clientOptions =
        ClusterClientOptions.builder()
            .autoReconnect(true)
            .topologyRefreshOptions(refreshOptions)
            .validateClusterNodeMembership(false)
            .build();

    LettuceClientConfiguration clientConfiguration =
        LettuceClientConfiguration.builder()
            .clientOptions(clientOptions)
            .readFrom(ReadFrom.REPLICA_PREFERRED)
            .useSsl()
            .build();

    return new LettuceConnectionFactory(clusterConfiguration, clientConfiguration);
  }

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    return builder -> {
      final RedisCacheConfiguration config =
          RedisCacheConfiguration.defaultCacheConfig()
              .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
              .serializeValuesWith(SerializationPair.fromSerializer(new JsonRedisSerializer()))
              .entryTtl(Duration.ofMinutes(30));
      builder.cacheDefaults(config);
      cacheProperties
          .getConfigByName()
          .forEach(
              (name, options) ->
                  builder.withCacheConfiguration(name, config.entryTtl(options.getTimeToLive())));
    };
  }
}
