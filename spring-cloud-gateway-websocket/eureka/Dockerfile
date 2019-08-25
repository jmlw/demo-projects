FROM maven:3-jdk-8-alpine AS build
WORKDIR /opt/src
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -Dmaven.test.skip=true spring-boot:repackage

FROM openjdk:8-jre-alpine
COPY --from=build /opt/src/target/eureka-0.0.1-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
