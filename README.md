# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/#build-image)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#boot-features-mongodb)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)

### Preface

This is a Spring Boot demo project.

It covers some aspects such as:

* REST API implementation and documentation (swagger)
* JPA using MySQL db and Caching implementation
* MongoDB connection
* Kafka messaging
* Spring Caching implementation
* Async method management
* Web Exception handling
* Testing (Unit & Integration tests)

### Preparing environments

#### Mongodb

```
docker run --name some-mongo -p 27017:27017 -d mongo:latest
```

See also:

https://hub.docker.com/_/mongo

#### MySQL

```
docker run --name=mysql1 -p 3306:3306 -d mysql/mysql-server
docker logs mysql1 2>&1 | grep GENERATED
<grep generated password>
docker exec -it mysql1 mysql -uroot -p
<enter generated password above>

mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';
mysql> create database db_example;
mysql> create user 'springuser'@'%' identified by 'ThePassword';
mysql> grant all on db_example.* to 'springuser'@'%';
```

See also: 

https://hub.docker.com/r/mysql/mysql-server/

#### Kafka

Build and run docker-compose.yaml by executing following command on project root:

```
docker compose up -d
```

Once kafka container has started, it is possible to 
listen message by executing one of the following:

```
docker exec -it development-kafka-1 ./opt/bitnami/kafka/bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic manual-topic
docker exec -it development-kafka-1 ./opt/bitnami/kafka/bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic manual-upper-topic
```