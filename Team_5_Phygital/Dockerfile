# Use the official Gradle image to create a build artifact.# This image will be used in the 'build' stage.

FROM gradle:jdk17-alpine as builder
# Set the working directory in the Docker image
WORKDIR /app
# Copy the Gradle executable to the Docker image
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
# Grant permissions to the Gradle wrapper
RUN chmod +x ./gradlew
# Copy the source code into the Docker image
COPY src src
# Build the JAR file
RUN ./gradlew build -x test
 Set the entrypoint script to initialize the container
ENTRYPOINT ["java", "-jar", "build/libs/my-phygital-boot-app.jar"]