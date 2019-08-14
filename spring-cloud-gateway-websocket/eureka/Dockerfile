FROM maven:3-jdk-8-alpine AS build
WORKDIR /opt/src
COPY pom.xml .
RUN mvn verify clean --fail-never
COPY src src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:8-jre-alpine
COPY --from=build /opt/src/target/websocket-gateway-demo-eureka-0.0.1-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
