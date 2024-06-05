

# Use the official Gradle image to create a build artifact
# This image will be used in the 'build' stage.
FROM gradle:jdk17-alpine as builder

# Set the working directory in the Docker image
WORKDIR /app

# Copy the Gradle executable to the Docker image
COPY --from=gradle /opt/gradle /opt/gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the source code into the Docker image
COPY src src

# Copy the test-related files and directories
COPY src/test src/test

# Build the JAR file
RUN ./gradlew bootJar

# Use OpenJDK for the final image.
FROM openjdk:17-alpine

# Set environment variables for PostgreSQL
ENV POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=admin \
    POSTGRES_DB=phygital \
    POSTGRES_HOST=35.189.209.222 \
    POSTGRES_PORT=5432

# Copy the JAR file from the 'builder' stage to this new stage
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Expose the port that the application listens on
EXPOSE 8080 8081

# Run the JAR file
ENTRYPOINT ["java","-jar","/app/app.jar"]
