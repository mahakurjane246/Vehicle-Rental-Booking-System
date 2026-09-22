# 🚗 Vehicle Rental & Booking System

A **microservices-based Vehicle Rental & Booking platform** built with Java, Spring Boot, and Spring Cloud. The system breaks a traditional monolithic rental application into independent, loosely-coupled services that communicate via **OpenFeign** and discover each other dynamically through **Eureka Service Discovery** — all routed through a single **API Gateway**.

---

## 📖 Table of Contents

- [Overview](#-project-overview)
- [Objectives](#-project-objectives)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Microservices](#-microservices)
- [Service Communication](#-service-to-service-communication)
- [Getting Started](#-getting-started)
- [Running the Services](#-running-the-services)
- [API Documentation](#-api-documentation)
- [Project Structure](#-project-structure)
- [Future Enhancements](#-future-enhancements)
- [Contributing](#-contributing)
- [License](#-license)

---

## 📌 Project Overview

The Vehicle Rental & Booking System provides a complete platform for managing:

| Entity | Description |
|---|---|
| 👤 **Customers** | Customer registration, profile, and account management |
| 🚗 **Vehicles** | Vehicle catalog, availability, and inventory management |
| 👨‍✈️ **Drivers** | Driver profiles and assignment to trips |
| 📅 **Bookings** | Vehicle reservation and booking lifecycle |
| 💳 **Payments** | Payment processing tied to bookings |
| 🛣️ **Trips** | Trip creation, tracking, and completion |
| ⭐ **Feedback** | Customer ratings and reviews on trips/bookings |

Rather than building this as a single monolithic application, each business capability is implemented as an **independent Spring Boot microservice** with its own database, business logic, and REST API — enabling independent development, deployment, and scaling.

---

## 🎯 Project Objectives

- ✅ Build a complete microservices-based application end-to-end
- ✅ Separate business responsibilities into independent, autonomous services
- ✅ Implement dynamic service discovery using **Eureka Server**
- ✅ Enable service-to-service communication using **OpenFeign**
- ✅ Provide centralized request routing through an **API Gateway**
- ✅ Implement REST APIs for full CRUD operations
- ✅ Persist data using **MySQL**
- ✅ Demonstrate a complete, realistic vehicle rental and booking workflow
- ✅ Eliminate hard-coded service IPs/ports through service discovery

---

## 🏗️ Architecture

```
                          ┌─────────────────────┐
                          │     API Gateway      │
                          │   (Spring Cloud GW)  │
                          └──────────┬───────────┘
                                     │
                     ┌───────────────┼───────────────┐
                     │               │               │
              ┌──────▼──────┐ ┌──────▼──────┐ ┌──────▼──────┐
              │  Eureka      │ │  Config      │ │  Load        │
              │  Server      │ │  Server      │ │  Balancer     │
              └──────┬──────┘ └─────────────┘ └─────────────┘
                     │
   ┌─────────┬───────┼───────┬───────────┬────────────┬─────────────┐
   │         │       │       │           │            │             │
┌──▼───┐ ┌──▼───┐ ┌─▼────┐ ┌▼───────┐ ┌──▼──────┐ ┌───▼──────┐ ┌────▼─────┐
│Customer│ │Vehicle│ │Driver│ │Booking │ │Payment  │ │Trip      │ │Feedback  │
│Service │ │Service│ │Service│ │Service │ │Service  │ │Service   │ │Service   │
└───┬───┘ └───┬───┘ └──────┘ └───┬────┘ └────┬────┘ └────┬─────┘ └────┬─────┘
    │         │                  │            │            │            │
    │         │◄─────────────────┘            │            │            │
    │◄──────────────────────────────────────────────────────────────────┘
        (OpenFeign calls, resolved via Eureka Service Discovery)
```

Each service registers itself with Eureka on startup, so downstream services can call it **by name** (not by hardcoded host/port) via OpenFeign.

---

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Microservices Orchestration | Spring Cloud |
| Service Discovery | Netflix Eureka |
| Inter-Service Communication | OpenFeign |
| API Routing | Spring Cloud API Gateway |
| Database | MySQL |
| Build Tool | Maven |
| API Style | REST |

---

## 🧩 Microservices

| Service | Responsibility | Port (default) |
|---|---|---|
| **Eureka Server** | Service registry & discovery | `8761` |
| **API Gateway** | Single entry point, request routing | `8080` |
| **Customer Service** | Manages customer data & accounts | `8081` |
| **Vehicle Service** | Manages vehicle inventory & availability | `8082` |
| **Driver Service** | Manages driver profiles & assignments | `8083` |
| **Booking Service** | Handles vehicle booking lifecycle | `8084` |
| **Payment Service** | Processes payments linked to bookings | `8085` |
| **Trip Service** | Manages trip creation & tracking | `8086` |
| **Feedback Service** | Manages customer ratings & reviews | `8087` |

> Adjust ports to match your actual `application.yml`/`application.properties` configuration.

---

## 🔗 Service-to-Service Communication

Communication between services is handled entirely through **OpenFeign clients**, with target services resolved dynamically via **Eureka** — no hardcoded IPs or ports.

```
Booking Service
   ├──── OpenFeign + Eureka ────► Customer Service
   └──── OpenFeign + Eureka ────► Vehicle Service

Payment Service
   ├──── OpenFeign + Eureka ────► Customer Service
   └──── OpenFeign + Eureka ────► Booking Service

Trip Service
   ├──── OpenFeign + Eureka ────► Vehicle Service
   └──── OpenFeign + Eureka ────► Driver Service

Feedback Service
   ├──── OpenFeign + Eureka ────► Customer Service
   ├──── OpenFeign + Eureka ────► Trip Service
   └──── OpenFeign + Eureka ────► Booking Service
```

**Example flow:** When a `Booking` is created, the Booking Service calls the Customer Service to validate the customer and the Vehicle Service to confirm vehicle availability — both resolved through Eureka at runtime.

---

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed on your machine:

- Java 17+ (or your target JDK version)
- Maven 3.8+
- MySQL 8+
- An IDE (IntelliJ IDEA / Eclipse / VS Code)
- Postman (for API testing)

### Clone the Repository

```bash
git clone https://github.com/<your-username>/vehicle-rental-booking-system.git
cd vehicle-rental-booking-system
```

### Database Setup

Create a separate MySQL database for each service (or a shared one, depending on your design):

```sql
CREATE DATABASE customer_db;
CREATE DATABASE vehicle_db;
CREATE DATABASE driver_db;
CREATE DATABASE booking_db;
CREATE DATABASE payment_db;
CREATE DATABASE trip_db;
CREATE DATABASE feedback_db;
```

Update the `application.properties` / `application.yml` in each service with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<service_db>
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Running the Services

Start the services **in this order** so that discovery and routing work correctly:

1. **Eureka Server** — service registry must be up first
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```
2. **API Gateway**
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```
3. **Business Services** (any order, once Eureka is running)
   ```bash
   cd customer-service   && mvn spring-boot:run
   cd vehicle-service    && mvn spring-boot:run
   cd driver-service     && mvn spring-boot:run
   cd booking-service    && mvn spring-boot:run
   cd payment-service    && mvn spring-boot:run
   cd trip-service       && mvn spring-boot:run
   cd feedback-service   && mvn spring-boot:run
   ```

Once everything is running, confirm all services are registered at the Eureka dashboard:

```
http://localhost:8761
```

All API requests should go through the API Gateway:

```
http://localhost:8080/<service-path>/...
```

---

## 📡 API Documentation

Each service exposes its own REST endpoints under the gateway. Example (adjust to match your actual controllers):

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/customers` | Register a new customer |
| `GET` | `/api/vehicles` | List all available vehicles |
| `POST` | `/api/bookings` | Create a new booking |
| `GET` | `/api/bookings/{id}` | Get booking details |
| `POST` | `/api/payments` | Process a payment for a booking |
| `POST` | `/api/trips` | Start a new trip |
| `POST` | `/api/feedback` | Submit customer feedback |

> Tip: consider adding **Swagger/OpenAPI** (`springdoc-openapi`) to each service so live, interactive API docs are auto-generated at `/swagger-ui.html`.

---

## 📂 Project Structure

```
vehicle-rental-booking-system/
├── eureka-server/
├── api-gateway/
├── customer-service/
├── vehicle-service/
├── driver-service/
├── booking-service/
├── payment-service/
├── trip-service/
├── feedback-service/
└── README.md
```

---

## 🔮 Future Enhancements

- [ ] Add **Spring Cloud Config Server** for centralized configuration
- [ ] Add **Resilience4j** for circuit breaking & fault tolerance between Feign calls
- [ ] Add **JWT-based authentication** at the API Gateway
- [ ] Add **Docker & Docker Compose** for containerized local setup
- [ ] Add **Swagger/OpenAPI** documentation per service
- [ ] Add **Kafka/RabbitMQ** for asynchronous, event-driven communication (e.g., booking → payment → notification)
- [ ] Add centralized logging & tracing (ELK stack / Zipkin / Sleuth)
- [ ] Add unit & integration tests per service

---

## 🤝 Contributing

Contributions are welcome! Please fork the repo, create a feature branch, and submit a pull request.

```bash
git checkout -b feature/your-feature-name
git commit -m "Add your feature"
git push origin feature/your-feature-name
```

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

### 👨‍💻 Author

Built as a demonstration of a real-world microservices architecture using Spring Boot, Spring Cloud, Eureka, and OpenFeign.
