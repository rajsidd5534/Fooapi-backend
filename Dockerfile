# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Install CA certificates for MongoDB Atlas SSL
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# Set working directory
WORKDIR /app

# Copy the JAR file from target folder
COPY target/app.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]
