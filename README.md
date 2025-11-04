<h1 align="center">🧾 Order Management System</h1>
<h3 align="center">A Spring Boot RESTful application with MySQL integration</h3>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.4.12-green?style=for-the-badge&logo=springboot">
  <img src="https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk">
</p>

---

## 📘 Project Overview

This is a **Spring Boot Order Management System**, designed to demonstrate key backend engineering concepts such as:
- RESTful API design
- Database integration using Spring Data JPA
- Entity relationships (One-to-Many, Many-to-One)
- Exception handling and validation
- Basic authentication for secure endpoints

It allows users to perform CRUD operations on **Customers** and **Orders** through REST APIs tested via Postman.

---

## 🏗️ Tech Stack

| Layer | Technology                   |
|-------|------------------------------|
| Backend | Spring Boot (Java 21)         |
| Database | MySQL                        |
| ORM | Hibernate / JPA              |
| Tools | Postman, Maven, Git          |
| Security | Spring Security (Basic Auth) |

---

## ⚙️ Features

✅ Add, fetch, update, and delete **Customers**  
✅ Manage **Orders** linked to each Customer  
✅ Validate inputs using `@Valid`, `@NotBlank`, etc.  
✅ Handle exceptions gracefully with `@ControllerAdvice`  
✅ Secure endpoints using Basic Authentication

---

## 🚀 Getting Started

### 1️⃣ Clone the repository

```bash
git clone https://github.com/SouDat/EC04.git
cd EC04
