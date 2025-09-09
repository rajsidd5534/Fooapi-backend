# Step 1: Use official Java image
FROM openjdk:17-jdk-slim

# Step 2: Set working directory
WORKDIR /app

# Install CA certificates for MongoDB Atlas SSL
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates


# Step 3: Copy the JAR file into the container
COPY target/app.jar app.jar

# Step 5: Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
