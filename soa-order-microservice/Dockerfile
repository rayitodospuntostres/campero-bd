FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests --no-transfer-progress

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/soa-order-microservice.jar soa-order-microservice.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "soa-order-microservice.jar"]
