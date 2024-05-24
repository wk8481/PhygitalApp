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
    implementation ("com.opencsv:opencsv:5.5.2")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation ("org.modelmapper:modelmapper:3.2.0")
    developmentOnly ("org.springframework.boot:spring-boot-devtools")
    runtimeOnly ("org.webjars:bootstrap:5.3.2")
    runtimeOnly ("com.h2database:h2")
    runtimeOnly ("org.postgresql:postgresql")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")





}

tasks.withType<Test> {
    useJUnitPlatform()
}
