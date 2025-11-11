# Library Management System (Java + SQLite)

A minimal, GUI-based library manager built with **Java Swing** and **SQLite**.
It lets you add books, list books, and "issue/return" them to a user.

## Features
- Add, list, and delete books
- Issue / Return (boolean toggle)
- SQLite database file created automatically (`library.db`)

## Requirements
- JDK 17+
- Maven 3.8+ (to auto-download dependencies)

## Run
```bash
mvn clean package
mvn exec:java -Dexec.mainClass="com.example.library.App"
```
If `exec-maven-plugin` isn't present, you can run:
```bash
mvn -q -DskipTests package
java -cp "target/library-management-system-1.0.0.jar:~/.m2/repository/org/xerial/sqlite-jdbc/3.46.0.0/sqlite-jdbc-3.46.0.0.jar" com.example.library.App
```

> Tip: On Windows, separate classpath entries with `;` instead of `:`.

## Project Structure
```
src/main/java/com/example/library/
  App.java
  DB.java
  Book.java
  BookDAO.java
  UI.java
```

## Next steps (good for your resume)
- Add Users table and link issued books to user IDs
- Add search + filters
- Add proper validation and unit tests
