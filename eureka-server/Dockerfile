
# Use an OpenJDK runtime as the base image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Maven/Gradle project files into the container
COPY build.gradle ./
COPY src ./src/

# install gradle
# RUN apt-get update && apt-get install -y unzip
# RUN curl -L https://services.gradle.org/distributions/gradle-6.5.1-bin.zip -o gradle-6.5.1-bin.zip
# RUN unzip gradle-6.5.1-bin.zip
# ENV GRADLE_HOME=/gradle/gradle-6.5.1
# ENV PATH=$PATH:$GRADLE_HOME/bin
# RUN gradle --version

# Set the working directory in the container
WORKDIR /app


# Build jar file
CMD ["./gradlew", "clean", "bootJar"]

# Use an OpenJDK runtime as the base image
# FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app


# Copy the JAR file into the container
COPY  /build/libs/*.jar eureka-server.jar

# Expose port 8761 to the outside world
EXPOSE 8761

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]