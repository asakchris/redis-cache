package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@Slf4j
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
