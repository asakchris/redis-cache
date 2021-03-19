package com.example.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.embedded.RedisServer;

public class Server {
  public static void main(String[] args) {
    final Logger log = LoggerFactory.getLogger(Server.class);
    int port = Integer.parseInt(System.getProperty("redis.server.port", "6379"));
    if (log.isInfoEnabled()) {
      log.info("port: {}", port);
    }
    RedisServer server = new RedisServer(port);
    if (log.isInfoEnabled()) {
      log.info("About to start Redis server in port {}", port);
    }
    server.start();
  }
}
