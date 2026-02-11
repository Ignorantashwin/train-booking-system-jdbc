# 🚆 Train Booking System (JDBC)

A backend-focused **Train Booking System** built using **Core Java, JDBC, and MySQL**.

This project demonstrates transaction management, concurrency-safe seat booking, and clean layered architecture — the core foundations required for backend development.

---

## 👨‍💻 Author

Ashwin   
GitHub: https://github.com/Ignorantashwin  

---

# ⭐ Project Highlights

- JDBC-based database integration  
- Proper transaction management (commit & rollback)  
- Concurrency-safe seat booking logic  
- Layered architecture (DAO → Service → App)  
- Environment variable-based DB configuration  
- Booking + Cancellation with atomic updates  
- Clean separation of business logic and database logic  

---

# 🏗 Tech Stack

- Java (Core)
- JDBC
- MySQL
- Gradle

---

# 🧠 Core Backend Concepts Implemented

## 1️⃣ Transaction Management

Booking and cancellation are handled using:

```java
con.setAutoCommit(false);
con.commit();
con.rollback();
```

This ensures:
- No partial updates  
- Data consistency  
- Safe failure handling  

---

## 2️⃣ Concurrency Protection (Prevents Overbooking)

Seat booking uses a safe conditional update:

```sql
UPDATE train
SET availableSeats = availableSeats - ?
WHERE trainNo = ? AND availableSeats >= ?
```

This prevents race conditions when multiple users try to book seats simultaneously.

---

## 3️⃣ Layered Architecture

```
entities → dao → service → App
```

- Entities → Model classes  
- DAO Layer → Database queries  
- Service Layer → Business logic  
- App → Console interaction  

---

# 📌 Features

- User Signup & Login  
- View Available Trains  
- Book Ticket  
- Cancel Ticket  
- View Booking History  
- Safe Seat Validation  
- Transaction Rollback on Failure  

---

# 🔐 Secure Configuration

Database credentials are NOT hardcoded.

They are loaded using environment variables:

DB_URL  
DB_USER  
DB_PASSWORD  

---

# 🚀 How To Run

1. Clone the repository  
2. Create MySQL database and required tables  
3. Set environment variables:
   - DB_URL
   - DB_USER
   - DB_PASSWORD
4. Run App.java  

---

# 📈 Future Improvements

- Spring Boot REST API version  
- JWT Authentication  
- Docker Support  
- Unit Testing  

---

# 🎯 Purpose of This Project

This project was built to strengthen backend fundamentals before moving to Spring Boot and REST API development.
