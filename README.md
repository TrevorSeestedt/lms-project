# LMS (Learning Management System) Project

## Overview
A comprehensive Java-based Learning Management System allowing students to take courses and quizzes while enabling authors to create and manage educational content.

## Features

### For Students
- Create and manage accounts
- Browse and enroll in courses
- Complete lessons and take quizzes
- Review courses and provide ratings
- Track progress and view grades

### For Authors
- Create and manage educational courses
- Organize content into modules and lessons
- Create quizzes with multiple-choice questions
- View student progress and grades
- Manage course reviews

### System Features
- Secure user authentication
- JSON-based data persistence
- Template-based initialization system
- Type-safe data handling

## System Requirements
- Java Development Kit (JDK) 8 or higher
- Windows, macOS, or Linux operating system

## Installation

### Quick Start (Windows)
1. Clone or download this repository
2. Double-click the `run-windows.bat` file
3. The system will automatically:
   - Create necessary directories
   - Check for template files
   - Compile the code
   - Run the application

### Manual Setup
1. Ensure the `json` directory exists (create it if necessary)
2. Compile the Java files:
   ```
   javac -d out -cp "./lib/json-simple-1.1.1.jar;./lib/junit-platform-console-standalone-1.9.2.jar" src/*.java
   ```
3. Run the application:
   ```
   java -cp "out;./lib/json-simple-1.1.1.jar;./lib/junit-platform-console-standalone-1.9.2.jar" src.CourseUI
   ```
   (Use colons instead of semicolons for Unix-based systems)

## Project Structure

### Key Directories
- `src/` - Java source files
- `lib/` - Required libraries (JSON parser and testing framework)
- `json/` - Data directory (created automatically)
- `out/` - Compiled classes (created during build)

### Key Source Files
- `CourseUI.java` - Main user interface
- `DataLoader.java` - Handles loading data from JSON files
- `DataWriter.java` - Manages writing data to JSON files
- `DataConstants.java` - Defines constants and manages file templates
- `Review.java` - Handles course reviews and ratings
- Various model classes (Course, Module, Lesson, Quiz, etc.)

### JSON Template System
The system uses template files for initial setup:
- `usersExample.json` - Template for user data
- `coursesExample.json` - Template for course data

These templates are automatically copied to working files (`json/users.json` and `json/course.json`) on first run.

## Usage

### Navigation
After starting the application, you'll see a menu-based interface. Use the number keys to select options.

### User Types
- **Student**: Can browse, enroll in, and take courses
- **Author**: Can create and manage courses and content

### Sample Accounts
The template includes sample accounts:
- Author: username `example_author`, password `password123`
- Student: username `example_student`, password `password123`

## Data Files
- User data: `json/users.json`
- Course data: `json/course.json`

These files are automatically created from templates if they don't exist.

## Testing
Run the included JUnit tests with:
```
java -jar lib/junit-platform-console-standalone-1.9.2.jar --class-path out --scan-class-path
```

## Development Notes
- The system uses JSON Simple for data persistence
- Data type handling has been improved to safely handle different numeric formats
- Reviews support both string and numeric ratings for flexibility
