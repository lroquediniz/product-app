# EmbeddedMongoTest Documentation

## Overview

The `EmbeddedMongoTest` class is an **enum-based utility** designed to manage an embedded MongoDB instance for testing purposes. It leverages the `de.flapdoodle.embed.mongo` library to start and stop an in-memory MongoDB instance. This is particularly useful for integration tests where a lightweight, isolated database environment is required.

## Features

- **Start and Stop Embedded MongoDB**: Provides methods to start and stop an embedded MongoDB instance.
- **Configurable Host and Port**: Allows customization of the MongoDB host, port, and IPv6 support.
- **MongoClient Integration**: Provides a `MongoClient` instance to interact with the embedded MongoDB.
- **Singleton Pattern**: Implements a singleton pattern using an enum to ensure a single instance of the embedded MongoDB.

## Class Details

### Package
The class is part of the package:
```
br.com.zup.productapp.test
```

### Dependencies
The class relies on the following libraries:
- **`de.flapdoodle.embed.mongo`**: For managing the embedded MongoDB instance.
- **`org.slf4j`**: For logging.
- **`com.mongodb.MongoClient`**: For MongoDB client operations.

### Enum Declaration
The class is implemented as an enum with a single instance:
```java
public enum EmbeddedMongoTest {
    DB;
}
```

This ensures a singleton instance (`DB`) for managing the embedded MongoDB.

## Methods

| Method Signature                          | Description                                                                                     |
|-------------------------------------------|-------------------------------------------------------------------------------------------------|
| `EmbeddedMongoTest port(int port)`        | Sets the port for the embedded MongoDB instance. Default is `27017`.                           |
| `EmbeddedMongoTest host(String host)`     | Sets the host for the embedded MongoDB instance. Default is `"localhost"`.                     |
| `EmbeddedMongoTest ipv6(boolean ipv6)`    | Enables or disables IPv6 support for the embedded MongoDB instance. Default is `false`.        |
| `void start()`                            | Starts the embedded MongoDB instance. Logs success or failure using SLF4J.                     |
| `void stop()`                             | Stops the embedded MongoDB instance. Logs success using SLF4J.                                 |
| `MongoClient getMongoClient()`            | Returns a `MongoClient` instance connected to the embedded MongoDB.                            |
| `int getPort()`                           | Returns the port on which the embedded MongoDB is running.                                     |
| `String getHost()`                        | Returns the host of the embedded MongoDB instance.                                             |
| `boolean isIPv6()`                        | Returns whether IPv6 is enabled for the embedded MongoDB instance.                             |

## Fields

| Field Name       | Type          | Description                                                                                     |
|-------------------|---------------|-------------------------------------------------------------------------------------------------|
| `mongodProcess`   | `MongodProcess` | Represents the running embedded MongoDB process.                                               |
| `active`          | `boolean`     | Indicates whether the embedded MongoDB instance is active.                                      |
| `mongoIPv6`       | `boolean`     | Indicates whether IPv6 is enabled for the embedded MongoDB instance.                           |
| `mongoHost`       | `String`      | The host address for the embedded MongoDB instance. Default is `"localhost"`.                  |
| `mongoPort`       | `int`         | The port for the embedded MongoDB instance. Default is `27017`.                                |

## Usage

### Starting the Embedded MongoDB
```java
EmbeddedMongoTest.DB.start();
```

### Stopping the Embedded MongoDB
```java
EmbeddedMongoTest.DB.stop();
```

### Configuring Host and Port
```java
EmbeddedMongoTest.DB.host("127.0.0.1").port(28017).ipv6(false).start();
```

### Getting a MongoClient
```java
MongoClient client = EmbeddedMongoTest.DB.getMongoClient();
```

### Checking Configuration
```java
int port = EmbeddedMongoTest.DB.getPort();
String host = EmbeddedMongoTest.DB.getHost();
boolean isIPv6 = EmbeddedMongoTest.DB.isIPv6();
```

## Insights

1. **Enum Singleton Pattern**: The use of an enum ensures thread safety and a single instance of the embedded MongoDB utility.
2. **Testing Utility**: This class is ideal for integration tests where a lightweight, in-memory MongoDB instance is required.
3. **Customizable Configuration**: The ability to configure host, port, and IPv6 support makes it flexible for various testing scenarios.
4. **Error Handling**: The `start()` method logs errors if the embedded MongoDB fails to start, ensuring visibility into issues during testing.
5. **Dependency on Flapdoodle**: The class relies on the `de.flapdoodle.embed.mongo` library, which is widely used for managing embedded MongoDB instances in Java applications.
