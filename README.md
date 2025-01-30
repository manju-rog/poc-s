# Enhanced Paginated Data

This project implements a **cursor-based pagination system** using **Spring Boot**, **JPA**, and a **responsive UI** with hover effects to show additional data.

## 📌 Features
- **Cursor-based Pagination**: Uses `anchorId` instead of offset-based pagination for better efficiency.
- **Dynamic Page Size**: User can change the number of records displayed per page.
- **Hover Effect on UI**: Displays `ID`, `Created At`, and `Payload` when hovered.
- **Search by Name (Optional)**: If a name is provided, the query fetches related records.
- **REST API with Postman Support**.

---
## 🔥 UI Preview

### **Pagination UI**
![Pagination UI](https://github.com/manju-rog/paging-jpa/assets/496090df-1ba2-4249-82f1-fce5af87b45d)


🔹 Each **Entity card** shows the `name` field.  
🔹 **Hovering over a card** displays extra details (`ID`, `Created At`, `Payload`).  
🔹 Buttons allow navigation with **previous and next** pagination.

---
## 🚀 Setup & Installation

### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/manju-rog/paging-jpa.git
cd paging-jpa
```

### **2️⃣ Configure Database**
Modify `src/main/resources/application.properties` with your database details:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### **3️⃣ Build & Run the Application**
```bash
mvn spring-boot:run
```
**OR** (if using Gradle)
```bash
gradle bootRun
```

The server starts at: `http://localhost:8080`

---
## 📌 API Documentation
### **GET /api/entities (Pagination API)**
#### ✅ Fetch Data Normally (First Page)
```http
GET http://localhost:8080/api/entities?anchorId=0&limit=10
```
**Response:**
```json
{
  "data": [
    { "id": 1, "name": "John Doe", "createdAt": "2025-01-29 10:11:09", "payload": "Sample payload 1" },
    { "id": 2, "name": "Jane Smith", "createdAt": "2025-01-29 10:15:09", "payload": "Sample payload 2" }
  ],
  "nextAnchor": 10,
  "currentLimit": 10
}
```

### **🔍 Fetch with Name Filter (Optional)**
```http
GET http://localhost:8080/api/entities?name=John&anchorId=0&limit=10
```
Response will contain **only records where name contains 'John'**.

### **📌 POSTMAN Example (Cursor-Based Pagination)**
✅ **Fetching 10 records after ID 50**
```http
GET http://localhost:8080/api/entities?anchorId=50&limit=10
```

---
## 📌 Database Schema
```sql
CREATE TABLE smart_entities (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payload VARCHAR(255) NOT NULL
);
```
✅ **Adding Data Using Sequence (`smart_seq`)** (For Oracle)
```sql
INSERT INTO smart_entities (id, name, created_at, payload)
VALUES (smart_seq.NEXTVAL, 'John Doe', TIMESTAMP '2025-01-29 10:11:09', 'Sample payload 1');
```

---
## 📌 How to Push Changes to GitHub
```bash![image](https://github.com/user-attachments/assets/d4ab0588-fdf5-4bb1-a85a-28ccbd3e1ae4)

git add .
git commit -m "Added pagination and UI updates"
git push origin master
```

---
## 🚀 Contributors
- **Manjunath** ([@manju-rog](https://github.com/manju-rog))

---
## 📌 License
This project is licensed under the MIT License.

