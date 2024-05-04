# Use the official Gradle image to create a build artifact.
# This image will be used in the 'build' stage.
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
RUN ./gradlew bootJar

# Use OpenJDK for the final image.
FROM openjdk:17-alpine

# Copy the JAR file from the 'builder' stage to this new stage
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Expose the port that the application listens on
EXPOSE 8080 8433 80 443 5432

# Run the JAR file
ENTRYPOINT ["java","-jar","/app/app.jar"]
