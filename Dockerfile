# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-jammy 

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files (if you use Maven wrapper)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies (this layer is cached)
RUN ./mvnw dependency:go-offline -B

# Copy the rest of your application code
COPY src ./src

# Package the application 
RUN ./mvnw package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "target/reservation-system-0.0.1-SNAPSHOT.jar"] 
# ^^^ Make sure this JAR filename matches yours!