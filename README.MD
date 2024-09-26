# Backend - Car Shop (Spring Boot + Maven)

This is the backend for the Car Shop application, built using Spring Boot and Maven.

# Tech Stack

- **Spring Boot**: Framework for building Java-based web applications.
- **Maven**: Build automation tool for managing dependencies.
- **H2 Database**: In-memory database for development and testing.
- **JPA**: Java Persistence API for managing data persistence.
- **Spring Security**: Secures the API endpoints.
- **REST API**: Communicates with the frontend via HTTP requests.

#  Getting Started

#  Prerequisites

**Make sure you have the following installed**:

- **Java**: JDK 11 or higher
- **Maven**

#  Installation

## 1. Clone the repository:
  
- git clone https://github.com/your-username/backend-repo.git

## 2. Navigate to the project directory:

- cd backend-repo

## 3. Build the project with Maven:

- mvn clean install

## Running the Project
### To run the backend application:

- mvn spring-boot:run

## The API will be available at http://localhost:8080.

# API Documentation
Once the backend is running, you can access the API documentation (generated using OpenAPI/Swagger) at:
http://localhost:8080/swagger-ui.html

# Database
The project uses an H2 in-memory database by default, which can be accessed at:


http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb**
- Username: 
- Password: password
- Testing

## To run the tests:

- mvn test
- Endpoints

## Here are some of the key API endpoints:

- GET /api/cars: Retrieves all cars
- POST /api/cars: Adds a new car
- PUT /api/cars/{id}: Updates car details
- DELETE /api/cars/{id}: Deletes a car
- Deployment