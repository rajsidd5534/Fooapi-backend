# ----------------------------
# Stage 1: Build JAR using Maven
# ----------------------------
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies (caching layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# ----------------------------
# Stage 2: Create runtime image
# ----------------------------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (change if needed)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

# Install CA certificates for MongoDB Atlas SSL
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

