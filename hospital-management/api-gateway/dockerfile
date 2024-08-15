# Use a base image with Java 11
FROM openjdk:11-jre-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Copy the application JAR file
COPY target/api-gateway-hospital-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]