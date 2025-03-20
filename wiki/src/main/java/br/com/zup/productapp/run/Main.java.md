# Documentation: Main.java

## Overview

This Java program serves as the entry point for a system that integrates with a MongoDB database and provides RESTful services for managing products. It uses the Spark framework for handling HTTP requests and includes an embedded MongoDB instance for database operations.

---

## File Metadata

- **File Name**: `Main.java`
- **Author**: Luan Roque
- **Purpose**: Execution class for the system.

---

## Code Structure

### 1. **Main Class**
The `Main` class is the execution class of the system. It initializes the necessary components, including the HTTP server and the MongoDB database connection.

### 2. **Methods**

| **Method**            | **Description**                                                                                     | **Access Modifier** |
|------------------------|-----------------------------------------------------------------------------------------------------|----------------------|
| `main(String[] args)`  | The main method that starts the system. It initializes static file handling and the product resource. | `public static`      |
| `mongo()`              | Configures and returns a MongoDB database instance.                                                | `private static`     |
| `mongoEmbedded()`      | Starts an embedded MongoDB instance.                                                               | `private static`     |

---

## Key Functionalities

### 1. **Static File Handling**
The program uses the Spark framework to serve static files from the `/public` directory:
```java
Spark.staticFileLocation("/public");
```

### 2. **Product Resource Initialization**
The `ProductResource` is initialized with a `ProductService` instance, which is configured with a MongoDB connection:
```java
new ProductResource(new ProductService(mongo()));
```

### 3. **MongoDB Configuration**
The `mongo()` method configures the MongoDB connection:
- Sets the logging level for MongoDB to `INFO`.
- Enables database tracing by setting the `DB.TRACE` system property.
- Starts an embedded MongoDB instance using the `mongoEmbedded()` method.
- Configures the MongoDB client with a connection pool of 20 connections per host.
- Connects to the database using constants defined in the `Constants` class.

### 4. **Embedded MongoDB**
The `mongoEmbedded()` method starts an embedded MongoDB instance using the `EmbeddedMongo` class:
```java
EmbeddedMongo.DB.start();
```

---

## Dependencies

### 1. **External Libraries**
- **MongoDB Java Driver**: Used for connecting to and interacting with MongoDB.
- **Spark Framework**: A lightweight web framework for handling HTTP requests.

### 2. **Custom Classes**
| **Class**               | **Description**                                                                 |
|--------------------------|---------------------------------------------------------------------------------|
| `EmbeddedMongo`          | Manages the lifecycle of an embedded MongoDB instance.                         |
| `ProductResource`        | Defines the RESTful endpoints for managing products.                           |
| `ProductService`         | Contains the business logic for product-related operations.                    |
| `Constants`              | Provides configuration constants such as database host, port, and name.        |

---

## Insights

1. **Thread Safety**: 
   - The `mongo()` and `mongoEmbedded()` methods are marked as `synchronized` to ensure thread safety during database initialization.

2. **Scalability**:
   - The MongoDB client is configured with a connection pool of 20 connections per host, which can handle moderate traffic.

3. **Embedded MongoDB**:
   - The use of an embedded MongoDB instance (`EmbeddedMongo`) suggests that the application is designed for local development or testing environments.

4. **Separation of Concerns**:
   - The program follows a modular design by separating the responsibilities of database configuration (`mongo()`), embedded database management (`mongoEmbedded()`), and HTTP request handling (`ProductResource`).

5. **Constants Usage**:
   - The `Constants` class centralizes configuration values, making the code more maintainable and reducing the risk of hardcoding.

---

## Potential Enhancements

1. **Environment-Specific Configuration**:
   - Introduce environment-based configurations (e.g., development, testing, production) to switch between embedded and external MongoDB instances.

2. **Error Handling**:
   - Add error handling for database connection failures and HTTP request processing.

3. **Logging**:
   - Enhance logging to include more detailed information about the application's runtime behavior.

4. **Security**:
   - Secure the MongoDB connection by enabling authentication and using encrypted communication (e.g., SSL/TLS).
