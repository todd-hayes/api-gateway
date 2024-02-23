# API Gateway Project

This project is developed using the following frameworks.
* Spring Boot 
* Spring MVC
* Spring Data JPA
* Spring Security
* Flyway

### Requirements
* Docker (Service and Database will deploy to container)
* Postgresql database

### Getting Started
There is a docker compose script to deploy the service and database instance. The tables are created using Flyway and loaded with sample data. There are two REST service APIs for managing users and roles. These are used by Spring Security for managing access the service endpoints. All endpoints require a login. The preloaded accounts can be used for using the service.

