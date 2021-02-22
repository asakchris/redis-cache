# Redis Java client examples using Spring Boot
Sample application using Redis cache using Spring Boot. Spring boot application 
acts as a Redis client and communicate with remote  Redis servers to store/retrieve cache.

### Implementation
Redis client interacts with servers using following ways:
##### Spring Data Redis
Interact with Redis using Spring data access technologies.
Refer [StockServiceImpl](client/src/main/java/com/example/client/service/StockServiceImpl.java)
for more details.

##### Spring Cache Manager
Interact with Redis using Spring cache manager.
Refer [StockExchangeServiceImpl](client/src/main/java/com/example/client/service/StockExchangeServiceImpl.java)
for more details.

##### Spring Cacheable
Interact with Redis using Spring cacheable annotation.
Refer [CountryServiceImpl](client/src/main/java/com/example/client/service/CountryServiceImpl.java)
for more details.

### Run
##### Redis Server
Follow below steps to start Redis server:
1. Refer the docker compose [file](docker-compose.yml), it has redis & redis commander service
1. Run `docker-compose up -d` command from root of the project to start redis server
1. Access Redis web management tool using this [link](http://localhost:8081)

##### Redis Client
Run [Application](client/src/main/java/com/example/client/Application.java) to start Redis Client

### Test
Refer endpoints in [Postman Collections](postman-collection/Redis.postman_collection.json)
