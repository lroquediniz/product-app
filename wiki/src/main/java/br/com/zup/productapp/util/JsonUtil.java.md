# Documentation: `JsonUtil.java`

## Overview

The `JsonUtil` class is a utility designed to transform Java objects into JSON strings. It implements the `ResponseTransformer` interface from the Spark framework, enabling seamless integration for JSON serialization in web applications. This class leverages the `Gson` library for JSON conversion.

---

## Class Details

### Package
The class is part of the package:
```
br.com.zup.productapp.util
```

### Purpose
The primary purpose of this class is to provide a reusable utility for converting Java objects into JSON strings, particularly in the context of HTTP responses in a Spark-based web application.

---

## Key Components

### Fields

| Field Name | Type       | Description                                                                 |
|------------|------------|-----------------------------------------------------------------------------|
| `gson`     | `Gson`     | An instance of the Gson library used for object-to-JSON serialization.      |

---

### Methods

#### `render(Object model)`

| Modifier & Type | Method Signature                     | Description                                                                                     |
|------------------|--------------------------------------|-------------------------------------------------------------------------------------------------|
| `public String`  | `render(Object model) throws Exception` | Converts the given object (`model`) into a JSON string. Handles special cases for `Response` objects. |

**Parameters:**
- `model`: The object to be serialized into JSON. If the object is an instance of `Response`, an empty JSON object (`{}`) is returned.

**Returns:**
- A JSON string representation of the input object.

**Throws:**
- `Exception`: If any error occurs during the serialization process.

**Logic:**
1. If the `model` is an instance of `Response`, the method returns an empty JSON object (`{}`).
2. Otherwise, it serializes the `model` object into a JSON string using the `Gson` library.

---

## Insights

1. **Integration with Spark Framework**:  
   The class implements the `ResponseTransformer` interface, making it compatible with Spark's response transformation mechanism. This allows developers to easily set up JSON serialization for HTTP responses.

2. **Special Handling for `Response` Objects**:  
   The `render` method includes a specific condition to handle instances of `Response`. In such cases, it returns an empty JSON object (`{}`). This behavior is likely intended to avoid serializing the `Response` object itself, which may not be meaningful in JSON format.

3. **Use of Gson Library**:  
   The class relies on the `Gson` library, a popular and lightweight library for JSON serialization and deserialization in Java. This ensures efficient and reliable JSON handling.

4. **Reusability**:  
   The `JsonUtil` class is designed to be a reusable utility, simplifying JSON serialization across the application.

5. **Thread Safety**:  
   The `gson` instance is not explicitly marked as thread-safe. While `Gson` itself is thread-safe for read operations, care should be taken if the instance is modified or extended in the future.

---

## Usage Example

Here is an example of how the `JsonUtil` class can be used in a Spark-based application:

```java
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        JsonUtil jsonUtil = new JsonUtil();

        get("/example", (req, res) -> {
            res.type("application/json");
            return new ExampleObject("Hello", "World");
        }, jsonUtil);
    }
}

class ExampleObject {
    private String key;
    private String value;

    public ExampleObject(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
```

In this example:
- The `JsonUtil` instance is used to transform the `ExampleObject` into a JSON string for the HTTP response.
- The `res.type("application/json")` ensures the response is sent with the correct `Content-Type` header.

---

## Metadata

| Key         | Value              |
|-------------|--------------------|
| **File Name** | `JsonUtil.java`   |
| **Author**    | Luan Roque        |
| **Purpose**   | JSON serialization utility for Spark applications |
