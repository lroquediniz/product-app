# Documentation: `ProductService.java`

## Overview
The `ProductService` class provides CRUD (Create, Read, Update, Delete) operations for managing `Product` entities in a MongoDB database. It interacts with the database using the MongoDB Java driver and utilizes the `Product` model for data representation. This service is designed to handle operations such as retrieving all products, creating new products, updating existing products, and deleting products by their unique identifier.

---

## Class Details

### **Class Name**: `ProductService`
- **Package**: `br.com.zup.productapp.service`
- **Author**: Lauan Roque
- **Purpose**: Provides CRUD operations for `Product` entities in MongoDB.

---

## Constructor

### `ProductService(DB db)`
- **Description**: Initializes the service with a MongoDB database connection.
- **Parameters**:
  - `db` (`DB`): The MongoDB database connection.
- **Behavior**: Sets up the `collection` field to reference the MongoDB collection defined in `ProductConstants.COLLECTION_NAME`.

---

## Methods

### `List<Product> findAll()`
- **Description**: Retrieves all products stored in the database.
- **Return Type**: `List<Product>`
- **Behavior**:
  - Queries the MongoDB collection for all documents.
  - Converts each document into a `Product` object and adds it to a list.
  - Returns the list of products.

---

### `void createNewProduct(String json)`
- **Description**: Creates a new product in the database using a JSON string.
- **Parameters**:
  - `json` (`String`): JSON representation of the product.
- **Behavior**:
  - Parses the JSON string into a `Product` object using Gson.
  - Inserts the product's fields (`name`, `description`, `price`, `category`) into the MongoDB collection.

---

### `Product find(String id)`
- **Description**: Finds a product in the database by its unique identifier.
- **Parameters**:
  - `id` (`String`): The unique identifier of the product.
- **Return Type**: `Product`
- **Behavior**:
  - Searches the MongoDB collection for a document with the specified `id`.
  - Converts the document into a `Product` object and returns it.

---

### `Product update(String id, String json)`
- **Description**: Updates an existing product in the database.
- **Parameters**:
  - `id` (`String`): The unique identifier of the product to be updated.
  - `json` (`String`): JSON representation of the updated product data.
- **Return Type**: `Product`
- **Behavior**:
  - Parses the JSON string into a `Product` object using Gson.
  - Constructs an update query with the new product fields (`name`, `description`, `price`, `category`).
  - Updates the document in the MongoDB collection matching the specified `id`.
  - Returns the updated product.

---

### `String delete(String id)`
- **Description**: Deletes a product from the database by its unique identifier.
- **Parameters**:
  - `id` (`String`): The unique identifier of the product to be deleted.
- **Return Type**: `String`
- **Behavior**:
  - Removes the document from the MongoDB collection matching the specified `id`.
  - Returns the `id` of the deleted product.

---

## Insights

### MongoDB Integration
- The class uses the MongoDB Java driver (`DB`, `DBCollection`, `DBObject`, etc.) for database operations.
- The `ObjectId` class is used to handle MongoDB's unique identifiers.

### JSON Parsing
- The `Gson` library is utilized for converting JSON strings into `Product` objects and vice versa.

### Product Model Dependency
- The class relies on the `Product` model and its constants (`ProductConstants`) for field names and data representation.

### CRUD Operations
- **Create**: Inserts new product data into the database.
- **Read**: Retrieves all products or a specific product by ID.
- **Update**: Modifies existing product data.
- **Delete**: Removes a product by ID.

### Error Handling
- The code does not explicitly handle errors or exceptions (e.g., invalid JSON, database connection issues, or missing documents). This could be improved for robustness.

### Scalability
- The current implementation assumes a single MongoDB collection for products. For scalability, additional features like indexing or sharding could be considered.

### Security Considerations
- The class does not validate input data (e.g., JSON strings or IDs). Input validation should be added to prevent potential security vulnerabilities like injection attacks.

---

## Dependencies

| **Dependency**       | **Purpose**                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| `com.mongodb.*`       | Provides MongoDB database interaction.                                     |
| `com.google.gson.*`   | Handles JSON serialization and deserialization.                            |
| `br.com.zup.productapp.model.Product` | Represents the `Product` entity and its constants.                          |

---

## Constants Used

| **Constant**                  | **Description**                          |
|-------------------------------|------------------------------------------|
| `ProductConstants.COLLECTION_NAME` | Name of the MongoDB collection for products. |
| `ProductConstants.FIELD_ID`        | Field name for the product's unique identifier. |
| `ProductConstants.FIELD_NAME`      | Field name for the product's name.             |
| `ProductConstants.FIELD_DESCRIPTION` | Field name for the product's description.     |
| `ProductConstants.FIELD_PRICE`     | Field name for the product's price.            |
| `ProductConstants.FIELD_CATEGORY`  | Field name for the product's category.         |
