# EmbeddedMongo Documentation

## Overview

The `EmbeddedMongo` class is an implementation of an embedded MongoDB instance using the Flapdoodle library. It provides functionality to start, stop, and configure an embedded MongoDB server for testing or development purposes. This class is implemented as an `enum` with a singleton instance (`DB`) to ensure a single embedded MongoDB instance is managed throughout the application lifecycle.

---

## Features

- **Embedded MongoDB Management**: Start and stop an embedded MongoDB instance.
- **Configuration Options**: Configure host, port, and IPv6 support for the embedded MongoDB instance.
- **MongoClient Integration**: Provides a method to retrieve a `MongoClient` instance connected to the embedded MongoDB server.

---

## Class Details

### Package
The class is part of the package:
```
br.com.zup.productapp.embedded
```

### Dependencies
The class relies on the following libraries:
- **Flapdoodle Embed Mongo**: Used to manage the embedded MongoDB instance.
- **MongoDB Java Driver**: Provides the `MongoClient` for database interaction.
- **SLF4J**: Used for logging.

### Enum Declaration
The class is declared as an `enum` with a single instance:
```java
public enum EmbeddedMongo {
    DB;
}
```

---

## Methods

### Configuration Methods

| Method Signature                     | Description                                                                 |
|--------------------------------------|-----------------------------------------------------------------------------|
| `EmbeddedMongo port(int port)`       | Sets the port for the embedded MongoDB instance. Default is `27017`.        |
| `EmbeddedMongo host(String host)`    | Sets the host for the embedded MongoDB instance. Default is `"localhost"`.  |
| `EmbeddedMongo ipv6(boolean ipv6)`   | Enables or disables IPv6 support for the embedded MongoDB instance.         |

### Lifecycle Methods

| Method Signature                     | Description                                                                 |
|--------------------------------------|-----------------------------------------------------------------------------|
| `void start()`                       | Starts the embedded MongoDB instance if it is not already active.           |
| `void stop()`                        | Stops the embedded MongoDB instance if it is active.                        |

### Utility Methods

| Method Signature                     | Description                                                                 |
|--------------------------------------|-----------------------------------------------------------------------------|
| `MongoClient getMongoClient()`       | Returns a `MongoClient` connected to the embedded MongoDB instance.         |
| `int getPort()`                      | Retrieves the configured port for the embedded MongoDB instance.            |
| `String getHost()`                   | Retrieves the configured host for the embedded MongoDB instance.            |
| `boolean isIPv6()`                   | Checks whether IPv6 is enabled for the embedded MongoDB instance.           |

---

## Attributes

| Attribute Name       | Type              | Description                                                                 |
|----------------------|-------------------|-----------------------------------------------------------------------------|
| `mongodProcess`      | `MongodProcess`  | Represents the running embedded MongoDB process.                           |
| `active`             | `boolean`        | Indicates whether the embedded MongoDB instance is active.                 |
| `mongoIPv6`          | `boolean`        | Specifies whether IPv6 is enabled for the embedded MongoDB instance.       |
| `mongoHost`          | `String`         | Hostname for the embedded MongoDB instance. Default is `"localhost"`.      |
| `mongoPort`          | `int`            | Port for the embedded MongoDB instance. Default is `27017`.                |

---

## Insights

1. **Singleton Design**: The use of an `enum` ensures a single instance (`DB`) of the embedded MongoDB server is managed throughout the application lifecycle. This is a thread-safe and efficient way to implement a singleton.

2. **Error Handling**: The `start()` method includes error handling to log failures when starting the embedded MongoDB instance. This ensures visibility into issues during initialization.

3. **Custom Configuration**: The class allows customization of host, port, and IPv6 settings, making it flexible for different environments.

4. **Testing Utility**: This class is particularly useful for integration testing, where a lightweight, in-memory MongoDB instance is required.

5. **Dependency on Flapdoodle**: The class relies on the Flapdoodle library, which is widely used for managing embedded MongoDB instances. Ensure the library is included in the project dependencies.

6. **Logging**: SLF4J is used for logging, providing clear messages for start and stop operations, as well as error scenarios.

---

## Usage Example

```java
public class EmbeddedMongoExample {
    public static void main(String[] args) {
        // Configure and start the embedded MongoDB instance
        EmbeddedMongo.DB.port(12345).host("127.0.0.1").ipv6(false).start();

        // Retrieve a MongoClient connected to the embedded MongoDB
        try (MongoClient mongoClient = EmbeddedMongo.DB.getMongoClient()) {
            System.out.println("Connected to Embedded MongoDB at " + EmbeddedMongo.DB.getHost() + ":" + EmbeddedMongo.DB.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Stop the embedded MongoDB instance
        EmbeddedMongo.DB.stop();
    }
}
```
