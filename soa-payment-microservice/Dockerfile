FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/soa-payment-microservice.jar payment.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "payment.jar"]