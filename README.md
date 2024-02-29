# Phygital Team 5 Integration Project

## Team Members
- Ruth Ishikanwa
- Noah Guerin
- William Kasasa
- Emre Caylar
- Lukas Nackmayr

## Project Overview
This project is part of the Integration course for Phygital Team 5. We are developing a Java application using Spring Boot and various technologies for our integration project.

### Current Build (Dev)
- **Database:** PostgreSQL
  - **URL:** `jdbc:postgresql://localhost:5444/phygital`
  - **Username:** `admin`
  - **Password:** `admin`
- **Main Application Properties:**
  ```properties
  server.port=8081
  spring.profiles.active=dev
  spring.jpa.open-in-view=false
  spring.jpa.show-sql=true
  spring.jpa.generate-ddl=true
  spring.jpa.hibernate.ddl-auto=create
  spring.jpa.defer-datasource-initialization=true
  spring.thymeleaf.enabled=true
  spring.thymeleaf.cache=false
  spring.thymeleaf.encoding=UTF-8
  spring.thymeleaf.mode=HTML
  spring.thymeleaf.prefix=classpath:templates/
  spring.thymeleaf.suffix=.html
  ```

## Database Connection
To connect to the database, use the following configuration:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5444/phygital
spring.datasource.username=admin
spring.datasource.password=admin
```

## Getting Started
### Cloning the Repository
To clone this repository, use the following command:
```bash
git clone https://gitlab.com/kdg-ti/integration-4/2023-2024/team-5/team-5-integration-4.git
```

### Building and Running
This project uses Gradle for building. Here's the `build.gradle` file:
```gradle
plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "be.kdg"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:postgresql:42.7.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
```

### Docker Configuration
We use Docker for our PostgreSQL database. Here's the `docker-compose.yml` file:
```yaml
version: '3.8'
services:
  postgres_phygital_db:
    image: postgres:16.1-alpine
    restart: always
    environment:
      POSTGRES_DB: 'phygital'
      POSTGRES_USER: 'admin'
      POSTGRES_PASSWORD: 'admin'
    ports:
      - '5444:5432'
```

### Running the Application
1. Make sure you have Docker installed.
2. Navigate to the project root directory.
3. Run the following command to start the PostgreSQL database:
   ```bash
   docker-compose up -d
   ```
4. Build and run the Spring Boot application using Gradle:
   ```bash
   ./gradlew bootRun
   ```

### Additional Notes
- This project includes Spring profiles for development and production. They are currently set to be identical for simplicity.
- Ensure that no unwanted files or directories are included in the repository by using a `.gitignore` file.

## Optional
- Implement your Domain Model as classes.
- Include Hibernate specifics, especially relationships.
- Create static HTML pages using Bootstrap 5.

---

This README should provide a clear guide for anyone working with your repository.
