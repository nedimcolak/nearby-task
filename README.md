# nearby-task
## Tech Stack
- API framework: Spring Boot
- Database: PostgreSQL
## Local development

**Project prerequisites**
> Before starting project locally make sure you have following:
> - **JDK 17 or later**
> - **PostgreSQL server installed on your machine**

#### Project setup
1. Create a PostgreSQL database on your machine
2. Open `src/main/resources/application.yml` file and update `spring.datasource` values

Provide your database credentials:
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/[DATABASE_NAME]
    username: YOUR_PG_USERNAME
    password: YOUR_PG_PASSWORD
```
3. Run the application

**Option 1 (using IDE):**

Navigate to `src/main/java/com/nearby/Application.java` and run `main` method.

**Option 2 (using terminal)**

From project root, execute following command:
```./gradlew bootRun```

```
API will be available on http://localhost:8080
```

Test API with Swagger: [Swagger UI](http://localhost:8080/swagger-ui/index.html)

##### Other(dev) dependencies used

- Lombok for generating getters, setters & constructors
- Liquibase as database versioning tool
- Spotless for code formatting & cleanup
