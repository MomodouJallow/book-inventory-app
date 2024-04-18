
#FROM openjdk:17-jdk-slim
#COPY /target/jallow-books-0.0.1-SNAPSHOT.jar jallow-books.jar
## ENV PORT=8080
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","jallow-books.jar"]


# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package

# Use a base image with OpenJDK JRE installed
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /app/target/jallow-books-0.0.1-SNAPSHOT.jar .

# Expose the port the Spring Boot application runs on
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "jallow-books-0.0.1-SNAPSHOT.jar"]
