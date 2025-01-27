# Azure Blob Storage API

This project is a Spring Boot application that interacts with Azure Blob Storage. It provides REST APIs to list blobs, read blob contents, and retrieve blob metadata.

## Features

- **List Blobs**: Retrieve a list of blobs from a container in Azure Blob Storage.
- **Read Blob Content**: Read the content of a specific blob.
- **List Blobs with Content**: Retrieve a map of blob names and their corresponding content.

---

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Built With](#built-with)
- [License](#license)

---

## Getting Started

Follow these instructions to set up and run the project locally.

### Prerequisites

1. **Java 17**: Ensure you have JDK 17 or later installed.
2. **Maven**: Ensure you have Maven installed.
3. **Azure Storage Account**: Have access to an Azure Blob Storage account with the necessary permissions.

### Configuration

Update the `application.properties` file with your Azure Blob Storage details:

```properties
azure.storage.account-name=<YOUR_STORAGE_ACCOUNT_NAME>
azure.storage.container-name=<YOUR_CONTAINER_NAME>
azure.storage.sas-token=<YOUR_SAS_TOKEN>
server.port=8080
```

Replace the placeholders with your Azure Blob Storage credentials.

---

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-repo-name>.git
   cd <your-repo-name>
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application:
   - Open a browser or API client (e.g., Postman) and hit `http://localhost:8080/api/blob`.

---

## API Endpoints

### 1. List Blobs
- **URL**: `GET /api/blob/list`
- **Description**: Lists all blobs in the container.
- **Response**: `List<String>` containing blob names.

### 2. Read Blob Content
- **URL**: `GET /api/blob/read/{blobName}`
- **Description**: Reads the content of a specified blob.
- **Path Variable**: 
  - `blobName`: Name of the blob to read.
- **Response**: Blob content as a `String`.

### 3. List Blobs with Content
- **URL**: `GET /api/blob/list-contents`
- **Description**: Retrieves a map of blob names and their corresponding content.
- **Response**: `Map<String, String>` where keys are blob names, and values are their content.

---

## Example Usage

### Using cURL

1. **List Blobs**
   ```bash
   curl -X GET http://localhost:8080/api/blob/list
   ```

2. **Read Blob Content**
   ```bash
   curl -X GET http://localhost:8080/api/blob/read/sample.txt
   ```

3. **List Blobs with Content**
   ```bash
   curl -X GET http://localhost:8080/api/blob/list-contents
   ```

---

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - Backend framework
- [Azure Blob Storage](https://azure.microsoft.com/en-us/services/storage/blobs/) - Cloud storage

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contributing

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

---









