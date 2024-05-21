# Use an official Maven image with JDK 18 for the build stage
FROM maven:3.8.5-openjdk-18 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and the source code to the container
COPY pom.xml /app/
COPY src /app/src
COPY resources /app/resources

# Package the application
RUN mvn clean package

# Use an official OpenJDK image for the runtime stage
FROM openjdk:18-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file and resources from the build stage
COPY --from=build /app/target/json-parser-1.0-SNAPSHOT.jar /app/json-parser-1.0-SNAPSHOT.jar
COPY --from=build /app/resources/input.txt /app/resources/input.txt

# Specify the command to run the application
CMD ["java", "-jar", "/app/json-parser-1.0-SNAPSHOT.jar", "/app/resources/input.txt"]