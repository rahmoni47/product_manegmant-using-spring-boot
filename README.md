# Product Management API

A robust RESTful API for product management built with **Spring Boot 3**, **Spring Data JPA**, and **MySQL**. This application provides complete CRUD (Create, Read, Update, Delete) functionality with structured service queries and centralized global exception handling.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Architecture](#-project-architecture)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
  - [1. Clone Repository](#1-clone-repository)
  - [2. Database Configuration](#2-database-configuration)
  - [3. Build & Run](#3-build--run)
- [API Endpoints](#-api-endpoints)
  - [Get All Products](#1-get-all-products)
  - [Create Product](#2-create-a-product)
  - [Update Product](#3-update-a-product)
  - [Delete Product](#4-delete-a-product)
- [Error Handling](#-error-handling)
- [License](#-license)

---

## ✨ Features

- **Full CRUD Operations**: Create, read, update, and delete products.
- **Clean Service Architecture**: Uses a Command/Query pattern via the `Query<I, O>` interface for decoupled business logic.
- **Global Exception Handling**: Centralized controller advice handling custom runtime exceptions with consistent error responses.
- **ORM & Data Persistence**: Hibernate/JPA integration with MySQL for database operations.
- **Lombok Integration**: Eliminates boilerplate code (getters, setters, constructors).

---

## 🛠 Tech Stack

- **Java**: 17
- **Framework**: Spring Boot 3.5.0
  - Spring Web
  - Spring Data JPA
  - Spring JDBC
  - Spring Boot DevTools
- **Database**: MySQL
- **Driver**: `mysql-connector-j`
- **Utility**: Project Lombok
- **Build Tool**: Apache Maven (Maven Wrapper included)

---

## 🏛 Project Architecture

```
Client (HTTP / REST)
       │
       ▼
ProductController
       │
       ├───► GetAllProducts (Query<Void, List<Product>>)
       ├───► CreateProduct  (Query<Product, ProductDTO>)
       ├───► UpdateProduct  (Query<UpdateProd, ProductDTO>)
       └───► DeleteProduct  (Query<Integer, Void>)
                 │
                 ▼
          ProductRepo (JpaRepository)
                 │
                 ▼
          MySQL Database
```

---

## 📁 Project Structure

```
product_manegmant-using-spring-boot/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── src/
    ├── main/
    │   ├── java/com/product_managmanent/
    │   │   ├── Projec01Application.java        # Main Spring Boot application entry point
    │   │   ├── Controller/
    │   │   │   └── ProductController.java      # REST endpoints for products
    │   │   ├── Model/
    │   │   │   ├── Product.java                # JPA Entity representing the database table
    │   │   │   ├── ProductDTO.java             # Data Transfer Object for responses
    │   │   │   └── UpdateProd.java             # Request wrapper for update operations
    │   │   ├── Service/
    │   │   │   ├── Query.java                  # Generic service interface
    │   │   │   ├── GetAllProducts.java         # Fetch all products
    │   │   │   ├── CreateProduct.java          # Create a new product
    │   │   │   ├── UpdateProduct.java          # Update product by ID
    │   │   │   └── DeleteProduct.java          # Delete product by ID
    │   │   ├── repository/
    │   │   │   └── ProductRepo.java            # Spring Data JPA repository
    │   │   ├── enums/
    │   │   │   └── RuntimeErrors.java          # Predefined application error messages
    │   │   └── exceptions/
    │   │       ├── ErrorClass.java             # Custom runtime exception
    │   │       └── handler.java                # @ControllerAdvice for centralized error handling
    │   └── resources/
    │       └── application.properties          # Application and datasource configuration
    └── test/
        └── java/com/product_managmanent/projec_01/
            └── Projec01ApplicationTests.java  # Spring Boot context test
```

---

## ⚙️ Prerequisites

Before running this application, make sure you have the following installed:

- **JDK 17** or later: [Download Java](https://www.oracle.com/java/technologies/downloads/)
- **MySQL Server** (8.0+ recommended): [Download MySQL](https://dev.mysql.com/downloads/)
- **Git**: [Download Git](https://git-scm.com/)

---

## 🚀 Getting Started

### 1. Clone Repository

```bash
git clone https://github.com/rahmoni47/product_manegmant-using-spring-boot.git
cd product_manegmant-using-spring-boot
```

### 2. Database Configuration

1. Create a MySQL database named `spring`:
   ```sql
   CREATE DATABASE spring;
   ```

2. Update database credentials in [`src/main/resources/application.properties`](src/main/resources/application.properties):
   ```properties
   spring.application.name=projec_01

   spring.datasource.url=jdbc:mysql://localhost:3306/spring
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

### 3. Build & Run

Using the included Maven wrapper:

**Linux / macOS:**
```bash
./mvnw clean spring-boot:run
```

**Windows:**
```cmd
mvnw.cmd clean spring-boot:run
```

Alternatively, build the JAR file and run it:
```bash
./mvnw clean package
java -jar target/projec_01-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`.

---

## 📡 API Endpoints

**Base URL**: `http://localhost:8080/product`

| Method | Endpoint | Description | Response Status |
| :--- | :--- | :--- | :--- |
| `GET` | `/product/` | Retrieve all products | `200 OK` |
| `POST` | `/product/` | Create a new product | `201 CREATED` |
| `PUT` | `/product/{id}` | Update product by ID | `200 OK` |
| `DELETE` | `/product/{id}` | Delete product by ID | `204 NO CONTENT` |

---

### 1. Get All Products

- **Method**: `GET`
- **URL**: `/product/`
- **Description**: Returns a list of all products in the database.

**Sample Request**:
```bash
curl -X GET http://localhost:8080/product/
```

**Sample Response (`200 OK`)**:
```json
[
  {
    "prod_id": 1,
    "prod_name": "Mechanical Keyboard",
    "prod_price": 79.99
  },
  {
    "prod_id": 2,
    "prod_name": "Wireless Mouse",
    "prod_price": 29.5
  }
]
```

---

### 2. Create a Product

- **Method**: `POST`
- **URL**: `/product/`
- **Headers**: `Content-Type: application/json`

**Sample Request Body**:
```json
{
  "prod_name": "Wireless Headset",
  "prod_price": 59.99
}
```

**Sample cURL**:
```bash
curl -X POST http://localhost:8080/product/ \
  -H "Content-Type: application/json" \
  -d '{
    "prod_name": "Wireless Headset",
    "prod_price": 59.99
  }'
```

**Sample Response (`201 CREATED`)**:
```json
{
  "prod_name": "Wireless Headset",
  "prod_price": 59.99
}
```

---

### 3. Update a Product

- **Method**: `PUT`
- **URL**: `/product/{id}`
- **Headers**: `Content-Type: application/json`

**Sample Request Body**:
```json
{
  "prod_name": "Pro Wireless Headset",
  "prod_price": 69.99
}
```

**Sample cURL**:
```bash
curl -X PUT http://localhost:8080/product/1 \
  -H "Content-Type: application/json" \
  -d '{
    "prod_name": "Pro Wireless Headset",
    "prod_price": 69.99
  }'
```

**Sample Response (`200 OK`)**:
```json
{
  "prod_name": "Pro Wireless Headset",
  "prod_price": 69.99
}
```

---

### 4. Delete a Product

- **Method**: `DELETE`
- **URL**: `/product/{id}`

**Sample cURL**:
```bash
curl -X DELETE http://localhost:8080/product/1
```

**Sample Response (`204 NO CONTENT`)**:
*Empty body*

---

## ⚠️ Error Handling

The application features a centralized exception handler ([`handler.java`](src/main/java/com/product_managmanent/exceptions/handler.java)) that intercepts [`ErrorClass`](src/main/java/com/product_managmanent/exceptions/ErrorClass.java) exceptions and returns a consistent JSON payload.

**When a product is not found (e.g. updating or deleting an invalid ID):**

- **HTTP Status**: `404 NOT FOUND`
- **Response Body**:
```json
{
  "Error : ": "Product not found",
  "status": 404,
  "Descreption : ": "Error when do operation"
}
```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

