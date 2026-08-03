# QAP 4 - Advanced Java: Persisting Data with a Database and a Text File

## Project Description
This project demonstrates two common Java persistence techniques:

- Writing Java objects to a text file using file I/O
- Writing Java objects to a PostgreSQL database using JDBC

The program uses a simple menu-driven console interface so the user can save and read data in both formats.

## What This Application Does
The application includes:

- Two custom entity classes: Drug and Patient
- A menu-based interface for user interaction
- File persistence for drug data
- Database persistence for patient data
- Reading and displaying saved records from both the file and the database

## Features
- Save drug data to a text file
- Read all saved drug records from the text file
- Save patient data to a PostgreSQL database
- Read all saved patient records from the database
- Validate input for IDs and dates

## Technologies Used
- Java
- Maven
- PostgreSQL
- JDBC
- File I/O

## Project Structure
- Java/ - Main Java classes
  - MenuApp.java
  - FileService.java
  - DatabaseService.java
  - Drug.java
  - Patient.java
- data/ - Text file storage for drug records
- Resources/ - SQL scripts for database setup
- pom.xml - Maven project configuration

## Database Setup
1. Create a PostgreSQL database named qap4_java.
2. Run the SQL script in Resources/schema.sql to create the required tables.
3. Optionally, use Resources/test_data.sql to load sample records.
4. Ensure the PostgreSQL server is running locally on port 5432.

## Running the Program
From the project root, run:

```bash
mvn exec:java
```

## Menu Options
When the program starts, the user can choose from the following options:

1. Save drug data to file
2. Read drug data from file
3. Save patient data to database
4. Read patient data from database
5. Exit

## Example Workflow
- Enter a drug record to save it to data/drugs.txt
- Read the saved drug information back from the file
- Enter a patient record to save it to PostgreSQL
- Retrieve the stored patient records from the database

## Reflection 
This assignment was good practice in understand how Java applications can persist data using both local files and relational databases. It showed how file-based storage and database storage each have different uses in real-world software development. The project also improved my understanding of Java classes, file handling and JDBC.

## Assignment Follow-up Questions

**1. How many hours did it take you to complete this assessment?**

This assignment took me roughly 3-4 hours to complete.

**2. What online resources did you use?**

I have used YouTube videos as my primary guide when practicing.

**3. Did you need to ask any classmates for help?**

I did not need to ask any friends for help solving problems.

**4. Did you need to ask any instructors for help?**

I did not need to ask any questions to instructors for help solving problems.

**5. Difficulty and confidence**

I feel confident in the current level of skills represented in this assessment.

Thank you for the practice and any tips!
