FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn -f pom.xml clean package

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /target/*.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]