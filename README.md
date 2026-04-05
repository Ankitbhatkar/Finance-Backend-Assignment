# 💰Finance-Backend-Assignment
 

A **Spring Boot REST API** for managing personal financial records with secure authentication using JWT and role-based access control.

---

## 🚀 Features

* 🔐 JWT Authentication (Register/Login)
* 👤 Role-Based Access Control (ADMIN, ANALYST, VIEWER)
* 💵 CRUD Operations for Financial Records
* 🔍 Filter Records (Type, Category, Date Range)
* 🗄️ PostgreSQL Integration
* 📦 Layered Architecture (Controller, Service, Repository)
* ✅ DTO-based Request/Response Handling

---

## 🛠️ Tech Stack

* ☕ Java 17
* 🌱 Spring Boot
* 🔐 Spring Security + JWT
* 🐘 PostgreSQL
* 📦 Maven
* 🧰 Lombok

---

## 📁 Project Structure

```
src/main/java/com/example/finance
│
├── controller       # REST Controllers
├── service          # Business Logic
├── repository       # Database Layer
├── entity           # JPA Entities
├── dto              # Data Transfer Objects
├── security         # JWT & Security Config
└── config           # Application Config
```

---

## 🔐 Authentication APIs

### ✅ Register User

POST /auth/register

**Request Body**

```json
{
  "username": "ankit",
  "password": "1234",
  "role": "ADMIN"
}
```

---

### ✅ Login User

POST /auth/login

**Response**

```json
{
  "token": "your_jwt_token"
}
```

---

## 💰 Financial Record APIs

> ⚠️ All APIs require JWT Token in header

Authorization:

```
Bearer your_jwt_token
```

---

### ➕ Create Record

POST /records

---

### 📄 Get All Records

GET /records

---

### 🔍 Get Record By ID

GET /records/{id}

---

### ✏️ Update Record

PUT /records/{id}

---

### ❌ Delete Record (ADMIN only)

DELETE /records/{id}

---

### 🔎 Filter Records

GET /records/filter

**Query Params Example**

```
?type=income&category=salary&startDate=2024-01-01&endDate=2024-12-31
```

---

## 🔑 Roles & Permissions

| Role    | Access       |
| ------- | ------------ |
| ADMIN   | Full Access  |
| ANALYST | Read + Write |
| VIEWER  | Read Only    |

---

## ⚙️ Configuration

### application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/finance_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret_key
```


## ▶️ Running the Project

### 1️⃣ Clone Repository

```
git clone https://github.com/Ankitbhatkar/Finance-Backend-Assignment.git
```

### 2️⃣ Navigate to Project

```
cd Finance-Backend-Assignment
```

### 3️⃣ Run Application

```
mvn spring-boot:run
```

---

## 🧪 Testing (Postman)

1. Register a user
2. Login to get JWT token
3. Add token in headers
4. Test all APIs

---

## ❗ Common Issues & Fixes

### 🔴 PostgreSQL Error: "relation User does not exist"

* `USER` is a reserved keyword
* Rename table to `users`

---

### 🔴 JWT Secret Key Error

* Secret must be at least 32 characters
* Use secure key with HS256 algorithm

---

## 🚀 Future Enhancements

* 📊 Dashboard & Analytics
* 📁 Export Reports (PDF/Excel)
* 🌐 Frontend Integration (React)
* 📅 Budget Tracking

---

## 👨‍💻 Author

**Ankit Bhatkar**

* 📧 Email: [ankitbhatkar693@gmail.com](mailto:ankitbhatkar693@gmail.com)
* 💼 LinkedIn:https://www.linkedin.com/in/ankit-p-bhatkar/
* 💻 GitHub: https://github.com/Ankitbhatkar

---

## ⭐ Support

If you found this project helpful, give it a ⭐ on GitHub!
