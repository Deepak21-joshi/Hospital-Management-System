# 🏥 Hospital Management System

A simple **Java + MySQL console-based application** to manage patients, doctors, and appointments.  
This project demonstrates **JDBC connectivity, SQL operations, and object-oriented programming** concepts in Java.

---

## 📌 Features
- Add new patients with details (Name, Age, Gender).
- View list of all registered patients.
- View doctors and their specializations.
- Book appointments for patients with doctors on a given date.
- Check doctor availability before booking.
- Data persistence with MySQL database.

---

## 🛠️ Tech Stack
- **Java (JDK 8 or higher)**
- **MySQL (8.x recommended)**
- **JDBC Driver** (`mysql-connector-j`)
- **IntelliJ IDEA / Eclipse** (any Java IDE)
- **Git & GitHub** (for version control)

---

## 📂 Project Structure
HospitalManagementSystem/
│
├── src/HospitalManagementSystem/
│ ├── HospitalSystem.java # Main class with menu
│ ├── Patient.java # Patient-related operations
│ ├── Doctor.java # Doctor-related operations
│
└── README.md # Project documentation


---

## ⚙️ Database Setup
1. Create a MySQL database:
   ```sql
   CREATE DATABASE hospital_management;

   Create patients table:

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);


Create doctors table:

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);


Create appointments table:

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
****************************************************************************
▶️ How to Run

Clone the repository:

git clone https://github.com/Deepak21-joshi/Hospita-Management-System.git


Open in IntelliJ IDEA or Eclipse.

Update database credentials in HospitalSystem.java:

private static final String url="jdbc:mysql://localhost:3306/hospital_management";
private static final String username="root";
private static final String password="your_mysql_password";


Compile and run:

javac HospitalManagementSystem/*.java
java HospitalManagementSystem.HospitalSystem

*******************************************************************************

🎯 Future Enhancements

Add update and delete features for patients and doctors.

Add appointment cancellation.

Create a GUI version with JavaFX or Spring Boot + Web.

Implement role-based login system (Admin, Doctor, Patient).

👨‍💻 Author

Deepak Joshi (DJ)
🚀 Java Developer | Problem Solver | Always Learning
