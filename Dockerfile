# First Stage (build)
FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
RUN mvn dependency:resolve

COPY src src
RUN mvn package

# Second Stage
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]