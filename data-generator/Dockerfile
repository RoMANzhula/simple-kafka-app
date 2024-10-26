FROM openjdk:22-jdk-slim as build

# install Maven byself
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# copy and build project
COPY /src /src
COPY pom.xml /
RUN mvn -f /pom.xml clean package

# running
FROM openjdk:22-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]