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
Run Redis server using anyone of following ways:
###### Embedded Server
- Refer [this](.run/Server.run.xml) run configuration and run it in Intellij
- Use system property `redis.server.port` to change server port if required
###### Docker
- Refer the docker compose [file](docker-compose.yml), it has redis & redis commander service
- Run `docker-compose up -d` command from root of the project to start redis server
- Access Redis web management tool using this [link](http://localhost:8082)
###### AWS
- Use AWS ElastiCache managed service to provision Redis cluster

##### Redis Client
- Refer [this](.run/Application.run.xml) run configuration and run it in Intellij to start Redis Client to connect local Redis server
- Refer [this](.run/Client-AWS.run.xml) run configuration and run it in Intellij to start Redis Client to connect AWS ElastiCache Redis cluster

### Test
Refer endpoints in [Postman Collections](postman-collection/Redis.postman_collection.json)
