# Product Class Documentation

## Overview

The `Product` class is a data model representing a product entity. It is designed to work with MongoDB, as evidenced by its use of `BasicDBObject` and `ObjectId`. The class provides constructors, getters, setters, and a builder pattern for creating instances. Additionally, it includes a nested interface for constant field names and a nested static class for building `Product` objects.

---

## Class Details

### Package
The class is part of the package:
```
br.com.zup.productapp.model
```

### Fields

| Field Name   | Type     | Description                          |
|--------------|----------|--------------------------------------|
| `id`         | `String` | Unique identifier for the product.  |
| `name`       | `String` | Name of the product.                |
| `description`| `String` | Description of the product.         |
| `price`      | `Double` | Price of the product.               |
| `category`   | `String` | Category to which the product belongs. |

---

## Constructors

### 1. Constructor with `BasicDBObject`
```java
public Product(BasicDBObject dbObject)
```
- **Purpose**: Initializes a `Product` object using a MongoDB `BasicDBObject`.
- **Parameters**:
  - `dbObject`: A `BasicDBObject` containing product data from MongoDB.

### 2. Constructor with Fields
```java
public Product(String id, String name, String description, Double price, String category)
```
- **Purpose**: Initializes a `Product` object with specific field values.
- **Parameters**:
  - `id`: Unique identifier for the product.
  - `name`: Name of the product.
  - `description`: Description of the product.
  - `price`: Price of the product.
  - `category`: Category of the product.

### 3. Default Constructor
```java
public Product()
```
- **Purpose**: Provides a no-argument constructor for creating an empty `Product` object.

### 4. Constructor with Builder
```java
public Product(ProductBuilder builder)
```
- **Purpose**: Initializes a `Product` object using the `ProductBuilder`.

---

## Methods

### Getters and Setters

| Method               | Description                          |
|----------------------|--------------------------------------|
| `getId()`            | Returns the product's ID.           |
| `setId(String id)`   | Sets the product's ID.              |
| `getName()`          | Returns the product's name.         |
| `setName(String name)`| Sets the product's name.           |
| `getDescription()`   | Returns the product's description.  |
| `setDescription(String description)`| Sets the product's description. |
| `getPrice()`         | Returns the product's price.        |
| `setPrice(Double price)`| Sets the product's price.        |
| `getCategory()`      | Returns the product's category.     |
| `setCategory(String category)`| Sets the product's category. |

---

## Nested Components

### 1. `ProductConstants` Interface
This interface defines constant field names for the `Product` class, which are used for MongoDB operations.

| Constant Name       | Value         | Description                          |
|---------------------|---------------|--------------------------------------|
| `FIELD_ID`          | `"_id"`       | Field name for the product ID.       |
| `FIELD_NAME`        | `"name"`      | Field name for the product name.     |
| `FIELD_DESCRIPTION` | `"description"`| Field name for the product description. |
| `FIELD_PRICE`       | `"price"`     | Field name for the product price.    |
| `FIELD_CATEGORY`    | `"category"`  | Field name for the product category. |
| `COLLECTION_NAME`   | `"products"`  | MongoDB collection name for products.|

### 2. `ProductBuilder` Class
A static nested class for building `Product` objects, primarily for testing purposes.

#### Fields
| Field Name   | Type     | Description                          |
|--------------|----------|--------------------------------------|
| `name`       | `String` | Name of the product.                |
| `description`| `String` | Description of the product.         |
| `price`      | `Double` | Price of the product.               |
| `category`   | `String` | Category of the product.            |

#### Constructor
```java
public ProductBuilder(String name, String description, Double price, String category)
```
- **Purpose**: Initializes the builder with product details.

#### Method
```java
public Product build()
```
- **Purpose**: Builds and returns a `Product` object using the builder's fields.

---

## Insights

1. **MongoDB Integration**: The class is tightly coupled with MongoDB, as it uses `BasicDBObject` for initialization and defines constants for MongoDB field names.
2. **Builder Pattern**: The `ProductBuilder` class simplifies the creation of `Product` objects, especially for testing or scenarios requiring immutability.
3. **Extensibility**: The use of constants in the `ProductConstants` interface ensures consistency and reduces the risk of errors when interacting with MongoDB.
4. **Default Constructor**: The presence of a no-argument constructor makes the class compatible with frameworks that require default constructors, such as Hibernate or Jackson.
