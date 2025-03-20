# ProductResource Documentation

## Overview

The `ProductResource` class is a RESTful resource that defines endpoints for managing products. It leverages the Spark Java framework to expose HTTP methods (`GET`, `POST`, `PUT`, `DELETE`) for interacting with product data. This class acts as a bridge between HTTP requests and the `ProductService`, which contains the business logic for handling product-related operations.

---

## File Metadata

- **File Name**: `ProductResource.java`
- **Author**: Luan Roque
- **Package**: `br.com.zup.productapp.resource`

---

## Purpose

The primary purpose of the `ProductResource` class is to define and expose RESTful endpoints for product management. It provides the following functionalities:

- Retrieve all products.
- Retrieve a specific product by its ID.
- Create a new product.
- Update an existing product.
- Delete a product by its ID.

---

## Key Components

### Constants

| Constant Name | Description                                                                 |
|---------------|-----------------------------------------------------------------------------|
| `PATH`        | Base URL path for product-related services (`/products`).                  |
| `PATH_PARAM`  | URL parameter for specifying the product ID (`:id`).                       |

### Dependencies

| Dependency Name       | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `ProductService`       | Service layer responsible for business logic related to product operations.|
| `Constants`            | Utility class containing application-wide constants, such as API context. |
| `JsonUtil`             | Utility class for handling JSON serialization and deserialization.         |

---

## Endpoints

The `setupEndpoints` method defines the following RESTful endpoints:

| HTTP Method | Endpoint                                      | Description                                                                                     | Response Status |
|-------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------|-----------------|
| `GET`       | `/api/products`                              | Retrieves all products.                                                                         | `200 OK`        |
| `GET`       | `/api/products/:id`                          | Retrieves a specific product by its ID.                                                        | `200 OK`        |
| `POST`      | `/api/products`                              | Creates a new product. The product data is provided in the request body.                        | `201 Created`   |
| `PUT`       | `/api/products/:id`                          | Updates an existing product identified by its ID. The updated data is provided in the request body. | `200 OK`        |
| `DELETE`    | `/api/products/:id`                          | Deletes a product identified by its ID.                                                        | `200 OK`        |

---

## Constructor

The `ProductResource` constructor initializes the class with a `ProductService` instance and sets up the endpoints.

### Parameters

| Parameter Name   | Type              | Description                                      |
|-------------------|-------------------|--------------------------------------------------|
| `productService`  | `ProductService` | Service instance for handling product operations.|

---

## Insights

1. **Framework Usage**: The class uses the Spark Java framework to define RESTful endpoints. This lightweight framework simplifies the creation of web services.
2. **Separation of Concerns**: The `ProductResource` class focuses solely on defining endpoints and delegating business logic to the `ProductService`. This ensures a clean separation of concerns.
3. **JSON Handling**: The `JsonUtil` utility is used to handle JSON serialization and deserialization, ensuring consistent data exchange between the client and server.
4. **Constants Utilization**: The use of the `Constants` class ensures that API context and media type definitions are centralized, promoting maintainability.
5. **HTTP Status Codes**: The class adheres to standard HTTP status codes (`200 OK`, `201 Created`) to indicate the result of operations, improving API usability.

---
