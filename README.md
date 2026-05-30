# LibraryManagementSystem
A Java + MySQL Library Management System with JDBC

# 📚 Library Management System

A console-based Library Management System built with Java and MySQL using JDBC.

## 🛠️ Technologies Used
- Java 17
- MySQL 8.0
- JDBC (MySQL Connector 9.7)
- IntelliJ IDEA

## ✨ Features
- ➕ Add / View / Delete Books
- 👨‍🎓 Add / View / Delete Students
- 📖 Borrow a Book
- 🔄 Return a Book
- 📋 View All Borrow Records

## 🗄️ Database Tables
| Table | Description |
|---|---|
| books | Stores all book details |
| students | Stores student information |
| borrow_records | Tracks borrowed and returned books |
| librarian | Stores librarian login details |

## ▶️ How to Run
1. Clone this repository
   git clone https://github.com/TejeshReddy955/LibraryManagementSystem.git
2. Open in IntelliJ IDEA
3. Create database in MySQL:
   CREATE DATABASE library_db;
4. Run SQL queries from database.sql file
5. Update your MySQL password in DatabaseConnection.java
6. Run Main.java

## 📸 Project Structure
src/
├── Main.java
├── Book.java
├── Student.java
├── BookDAO.java
├── StudentDAO.java
├── BorrowDAO.java
└── DatabaseConnection.java

## 👨‍💻 Author
**Tejesh Reddy**
- GitHub: [@TejeshReddy955](https://github.com/TejeshReddy955)
