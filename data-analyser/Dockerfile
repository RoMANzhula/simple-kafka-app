FROM maven:3.9.1-openjdk-22 as build

COPY /src /src
COPY pom.xml /
RUN mvn -f /pom.xml clean package


FROM openjdk:22-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]