# Gallery Management System

## Description
Rent-a-Car Service is a demo project for a car rental service built with Spring Boot.

## Project Structure
- **src/main/java/com/alimert**: Contains the main application code.
- **src/test/java**: Contains the test code.
- **pom.xml**: Maven configuration file.

## Getting Started

### Prerequisites
- Java 17
- Maven
### Dependencies
The project uses the following dependencies:
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Web
- JJWT (JSON Web Token) API
- PostgreSQL
- Lombok
- Spring Boot Starter Test
- Spring Security Test
- Springdoc OpenAPI

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/aliwert/rent-a-car-service.git
    cd rent-a-car-service
    ```
2. Build the project:
    ```sh
    mvn clean install
    ```
## Before run the project
* src/main/resources/application.properties
```
spring.application.name=spring-data-jpa
spring.datasource.url=jdbc:postgresql://localhost:5432/<DB_NAME>
spring.jpa.properties.hibernate.default_schema=<SCHEMA_NAME>
spring.datasource.username=<USERNAME>
spring.datasource.password=<DB_PASSWORD>

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
## After creating the database, the things you need to do
* When you run the project for the first time, set spring.jpa.hibernate.ddl-auto=create. After the table is created, don't forget to change it to update or none, but I recommend using update. <b>To prevent the added data from being deleted</b>
```
spring.jpa.hibernate.ddl-auto=update
```

### Running the Application
To run the application, use the following command:
```sh
mvn spring-boot:run
```




## Need to do
- First, you need to go to ``` http://localhost:8080/register``` and sign up. A username and passowrd are sufficient.
- Secondly, to obtain an access token, go to ```http://localhost:8080/authenticate```, enter your username and password, and after receiving the access token, you can perform CRUD operations by visiting any URL within the project.
- Finally, I should mention that when your access token expires, you need to go to ```http://localhost:8080/refreshtoken``` and obtain a new access token using the refresh token.
