# Simple Kafka application

This repository contains a multi-module Spring Boot application for data analysis, generation, and storage. Each module is designed to serve a specific purpose within the application, with components for data handling, analysis, and persistence.

## Technologies Used

### Spring Boot: Framework for Java applications.
### Apache Kafka: Messaging system for asynchronous data streaming.
### Reactor: Reactive programming library for JVM.
### PostgreSQL: Relational database for data persistence.
### Redis: In-memory data structure store, used as a database, cache, and message broker.

## Modules

### 1. data-analyser
- **Description**: This module is responsible for analyzing data and processing it based on the application's requirements.
- **Key Dependencies**:
    - `spring-boot-starter-web`: Enables RESTful web services.
    - `spring-kafka`: Integrates with Apache Kafka for messaging.
    - `reactor-kafka`: Reactor support for Kafka, enabling reactive message streaming.
    - `lombok`: Reduces boilerplate code with annotations for getters, setters, etc.
    - `jcabi-xml`: Provides XML parsing and manipulation.
    - `gson`: Used for JSON serialization and deserialization.
    - `spring-boot-starter-data-jpa`: JPA support for data persistence.
    - `postgresql`: Database driver for PostgreSQL.
    - `liquibase-core`: Manages database schema migrations.

### 2. data-generator
- **Description**: The data-generator module is designed to create synthetic data to be used in analysis or testing. This module interacts with Kafka for data streaming and generates data based on specified configurations.
- **Key Dependencies**:
    - `spring-boot-starter-web`: Provides RESTful API endpoints.
    - `spring-kafka` & `reactor-kafka`: Kafka integration for data streaming.
    - `mapstruct`: Used for object mapping.
    - `jcabi-xml`: XML manipulation.
    - `awaitility`: Testing framework to handle asynchronous code in tests.
- **Testing & Code Quality**:
    - `jacoco-maven-plugin`: Generates code coverage reports.
    - Coverage rules are set with minimum instruction coverage of 65%.

### 3. data-store
- **Description**: This module is responsible for data storage and retrieval, acting as the persistent layer of the application. It leverages a Redis database for caching and uses Kafka for message-based communication.
- **Key Dependencies**:
    - `spring-boot-starter-web`: Provides HTTP and REST support.
    - `spring-kafka`: Kafka integration.
    - `jedis`: Java client for Redis.
    - `lombok`: Code reduction through annotations.

## Docker-compose