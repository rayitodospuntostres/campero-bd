

# Deployment Guide for SOA Microservices

This guide provides step-by-step instructions to deploy and execute the SOA microservices developed in Java using Spring Boot.

## Microservices Overview

1. **soa-auth-microservice**  
   Handles authentication and authorization. Provides security mechanisms like JWT tokens for other services.

2. **soa-eureka-server**  
   Service discovery server based on Netflix Eureka. Central registry for all microservices, enabling dynamic discovery and load balancing.

3. **soa-gateway-microservice**  
   API Gateway that routes requests to appropriate microservices. Implements centralized logging, monitoring, and authentication.

4. **soa-order-microservice**  
   Manages orders and integrates with the order details service.

5. **soa-order_detail-microservice**  
   Handles order details and connects with the order service to provide detailed information.

6. **soa-payment-microservice**  
   Processes payments and ensures secure financial transactions.

7. **soa-product-microservice**  
   Manages product data, inventory, and catalog information.

8. **soa-user-microservice**  
   Manages user data, profiles, and related services.

---

## Prerequisites

1. **Java Development Kit (JDK)**: Ensure JDK 17 is installed.
2. **Maven**: For building and managing dependencies.
3. **Spring Boot**: Spring Boot runtime environment.
4. **Docker (optional)**: For containerized deployment.
5. **Database**: Configure appropriate databases for each service (e.g., MySQL).

6. **Databases**: Ensure the following databases are set up and configured:
    - `soa_user_microservice` for `soa-user-microservice`
    - `soa_product_microservice` for `soa-product-microservice`
    - `soa_order_microservice` for `soa-order-microservice`
    - `soa_order_detail_microservice` for `soa-order_detail-microservice`
    - `soa_payment_microservice` for `soa-payment-microservice`

```MySQL
      CREATE DATABASE soa_user_microservice;
      CREATE DATABASE soa_product_microservice;
      CREATE DATABASE soa_order_microservice; 
      CREATE DATABASE soa_order_detail_microservice;
      CREATE DATABASE soa_payment_microservice;
  ```

---

## Deployment Order

### 1. Start the Service Discovery Server
**Service**: `soa-eureka-server`  
- **Why**: All other services will register with the Eureka server for discovery.  
- **Steps**:
  1. Navigate to the `soa-eureka-server` directory.
  2. Build the service: `mvn clean install`.
  3. Run the service: `java -jar target/soa-eureka-server.jar`.

### 2. Deploy the Authentication Service
**Service**: `soa-auth-microservice`  
- **Why**: Ensures secure communication and provides tokens for authentication.  
- **Steps**:
  1. Navigate to the `soa-auth-microservice` directory.
  2. Build the service: `mvn clean install`.
  3. Run the service: `java -jar target/soa-auth-microservice.jar`.

### 3. Start the API Gateway
**Service**: `soa-gateway-microservice`  
- **Why**: Centralized entry point for all API requests and routing.  
- **Steps**:
  1. Navigate to the `soa-gateway-microservice` directory.
  2. Build the service: `mvn clean install`.
  3. Run the service: `java -jar target/soa-gateway-microservice.jar`.

### 4. Deploy the Core Microservices
Deploy the core services that handle business logic.  
- **Services**:
  - `soa-user-microservice`
  - `soa-product-microservice`
  - `soa-order-microservice`
  - `soa-order_detail-microservice`
  - `soa-payment-microservice`

- **Steps for Each**:
  1. Navigate to the respective microservice directory.
  2. Build the service: `mvn clean install`.
  3. Run the service: `java -jar target/{microservice-name}.jar`.

### 5. Test the System
- Verify that all services have registered with the Eureka server by accessing the Eureka dashboard (usually at `http://localhost:8761`).
- Ensure the API Gateway routes requests to the appropriate services.


Excute Microservices 
```
git clone https://github.com/teuzed/soa-microservices.git
cd soa-microservices
docker-compose up -d 
```




## **Endpoints de la API**

### **1. Registro de Usuario**

#### Endpoint:
**POST** `http://localhost:8099/api/v1/auth/save`

#### Body:
```json
{
  "name": "Ryan Wantuil Pacheco S",
  "username": "gpacheco@utp.edu.com",
  "password": "ryan"
}
```


### **2. Login Usuarios**

#### Endpoint:
**POST** `http://localhost:8099/api/v1/auth/login`

#### Body:
```json
{
  "username": "gpacheco@utp.edu.com",
  "password": "ryan"
}
```
###Respuesta esperada

```
{
   "token":"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXX"
}
```
### **3. Solicitud Autenticada**

GET http://localhost:8099/api/v1/user/example

Headers:
Authorization: Bearer <token>




