FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn clean package

ENTRYPOINT ["java","-jar","target/WeatherDispatcher-0.0.1-SNAPSHOT.jar"]
