FROM maven:3.9.1-openjdk-22 as build

COPY pom.xml .
RUN mvn dependency:go-offline
COPY /src /src
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", ".jar", "app.jar"]