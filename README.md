🚗 Vehicle Rental & Booking System

A Microservices-based Vehicle Rental & Booking System developed using Java, Spring Boot, Spring Cloud, Eureka Service Discovery, OpenFeign, API Gateway, REST APIs, MySQL, and Maven**.

The system is designed using a microservices architecture where each business responsibility is separated into an independent service. OpenFeign is used for service-to-service communication, while Eureka Server provides service discovery.

📌 Project Overview

The Vehicle Rental & Booking System provides a platform for managing:

👤 Customers
🚗 Vehicles
👨‍✈️ Drivers
📅 Vehicle Bookings
💳 Payments
🛣️ Trips
⭐ Customer Feedback

Instead of implementing the entire application as a single monolithic application, the system is divided into independent microservices.

Each service has its own business responsibility and communicates with other services using OpenFeign + Eureka Service Discovery.

🎯 Project Objectives

- Build a complete microservices-based application.
- Separate business responsibilities into independent services.
- Implement service discovery using Eureka Server.
- Implement service-to-service communication using OpenFeign.
- Provide centralized request routing through API Gateway.
- Implement REST APIs for CRUD operations.
- Use MySQL for persistent data storage.
- Demonstrate a complete vehicle rental and booking workflow.
- Avoid hard-coded service IP addresses and ports for service discovery.

🔗 Service-to-Service Communication

Booking Service
      │
      ├──── OpenFeign + Eureka ────► Customer Service
      │
      └──── OpenFeign + Eureka ────► Vehicle Service


Payment Service
      │
      ├──── OpenFeign + Eureka ────► Customer Service
      │
      └──── OpenFeign + Eureka ────► Booking Service


Trip Service
      │
      ├──── OpenFeign + Eureka ────► Vehicle Service
      │
      └──── OpenFeign + Eureka ────► Driver Service


Feedback Service
      │
      ├──── OpenFeign + Eureka ────► Customer Service
      │
      ├──── OpenFeign + Eureka ────► Trip Service
      │
      └──── OpenFeign + Eureka ────► Booking Service
