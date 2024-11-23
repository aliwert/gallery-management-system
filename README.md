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

## Need to do
- First, you need to go to ``` http://localhost:8080/register``` and sign up. A username and passowrd are sufficient.
- Secondly, to obtain an access token, go to ```http://localhost:8080/authenticate```, enter your username and password, and after receiving the access token, you can perform CRUD operations by visiting any URL within the project.
- Finally, I should mention that when your access token expires, you need to go to ```http://localhost:8080/refreshtoken``` and obtain a new access token using the refresh token.
