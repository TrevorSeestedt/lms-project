# LMS (Learning Management System) Project

## Project Overview
This is a Java-based Learning Management System (LMS) application that allows users to sign up, log in, enroll in courses, take quizzes, view grades, and more. Authors can create and manage courses, while students can take courses and track their progress.

## System Requirements
- Java Development Kit (JDK) 8 or higher
- Command line interface or terminal

## Project Structure
- `Tank/` - Source files
- `json/` - JSON data files for users and courses
- `lib/` - External libraries:
  - json-simple-1.1.1.jar - JSON parsing
  - junit-platform-console-standalone-1.9.2.jar - Unit testing

## How to Compile and Run

### 1. Compile the Project
Navigate to the project root directory and compile all Java files:

```bash
javac -cp "lib/*" Tank/*.java
```

### 2. Run the Application
Once compiled, run the application using:

```bash
java -cp ".:lib/*" Tank.CourseUI
```

For Windows, use semicolons instead of colons in the classpath:

```bash
java -cp ".;lib/*" Tank.CourseUI
```

### 3. Using the Application
- When the application starts, you'll see an initial screen with options to log in, sign up, or exit
- After logging in, the interface will change based on whether you're a student or an author
- Students can enroll in courses, view current courses, check grades, and view account information
- Authors can create and manage courses, modules, and quizzes

## Testing
To run the included unit tests:

```bash
java -jar lib/junit-platform-console-standalone-1.9.2.jar --class-path . --scan-class-path
```

## Data Files
- User data is stored in `json/users.json`
- Course data is stored in `json/course.json`
