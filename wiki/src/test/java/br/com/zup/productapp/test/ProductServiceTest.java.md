# Documentation: `ProductServiceTest.java`

## Overview

The `ProductServiceTest` class is a unit test suite designed to validate the functionality of the `ProductService` class. It uses an embedded MongoDB instance to perform CRUD (Create, Read, Update, Delete) operations on `Product` objects. The tests ensure that the service behaves as expected when interacting with the database.

---

## Class Details

### Class: `ProductServiceTest`

This class is responsible for testing the `ProductService` class. It includes setup and teardown methods for initializing and stopping an embedded MongoDB instance, as well as a test method for verifying CRUD operations.

---

## Key Components

### Fields

| Field Name | Type              | Description                          |
|------------|-------------------|--------------------------------------|
| `service`  | `ProductService`  | Instance of the service being tested.|

---

### Methods

#### 1. `beforeClass()`
- **Type**: `@BeforeClass`
- **Description**: Initializes the embedded MongoDB instance before any tests are executed.
- **Throws**: `Exception`

#### 2. `afterClass()`
- **Type**: `@AfterClass`
- **Description**: Stops the embedded MongoDB instance after all tests are completed.

#### 3. `crudTest()`
- **Type**: `@Test`
- **Description**: Tests the CRUD operations of the `ProductService` class.
- **Throws**: `Exception`
- **Steps**:
  1. Creates a `Product` object using the `ProductBuilder`.
  2. Converts the product to JSON using `JsonUtil` and saves it to the database.
  3. Retrieves all products and verifies the list is not empty.
  4. Finds a specific product by its ID and validates its fields.
  5. Updates the product's price and verifies the update.
  6. Deletes the product and ensures the deletion was successful.

#### 4. `mongoEmbedded()`
- **Type**: `private static synchronized`
- **Description**: Configures and returns an embedded MongoDB instance for testing.
- **Returns**: `DB` (MongoDB database instance)
- **Throws**: `Exception`

---

## Insights

1. **Embedded MongoDB Usage**:
   - The test suite uses an embedded MongoDB instance (`EmbeddedMongoTest.DB`) to simulate database operations. This ensures that the tests are isolated and do not depend on an external database.

2. **Product Validation**:
   - The `crudTest()` method performs comprehensive validation of the `Product` object, including its ID, name, description, price, and category.

3. **Builder Pattern**:
   - The `Product` object is created using the builder pattern (`Product.ProductBuilder`), which improves code readability and ensures immutability.

4. **JSON Serialization**:
   - The `JsonUtil` class is used to serialize and deserialize `Product` objects to and from JSON format, ensuring compatibility with the service's expected input and output.

5. **Logging**:
   - MongoDB logging is configured to `INFO` level to reduce verbosity during tests.

6. **Constants Usage**:
   - Database connection details (e.g., host, port, and database name) are retrieved from the `Constants.DataSource` class, promoting reusability and maintainability.

---

## Dependencies

| Dependency         | Purpose                                                                 |
|--------------------|-------------------------------------------------------------------------|
| `org.junit`        | Provides annotations and assertions for unit testing.                  |
| `com.mongodb`      | MongoDB driver for database operations.                                |
| `br.com.zup.*`     | Custom classes for the application, including `ProductService` and `Product`. |
| `EmbeddedMongoTest`| Utility for managing the embedded MongoDB instance during tests.       |

---

## Test Coverage

The `ProductServiceTest` class covers the following functionalities of the `ProductService`:
- **Create**: Adding a new product to the database.
- **Read**: Retrieving all products and finding a specific product by ID.
- **Update**: Modifying an existing product's details.
- **Delete**: Removing a product from the database.

---

## Limitations

- The test suite assumes the presence of the `EmbeddedMongoTest` utility for managing the embedded MongoDB instance.
- The `Constants.DataSource` class must provide valid database connection details for the embedded MongoDB instance.
