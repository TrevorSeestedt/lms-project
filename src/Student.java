package src;

import java.util.UUID;
import java.util.ArrayList;

public class Student extends RegisteredUser {
   
    /**
    * A class for a currently enrolled student that sets their 
    * @param userID a user's ID
    * @param type a String to determine if a user is a student or author.
    * @param firstName a String for the user's first name.
    * @param lastName a String for the user's last name.
    * @param username a String for the user's username for login.
    * @param password a String for the user's password.
    * @param email a String of the user's electronic mailing address.
    * @param DOB a String for the user's birth date.
    * @param currentCourses an array of the courses the use is currently enrolled in.
    * @param grades a double value of the student's grades after completing assignments
    */
    public Student(UUID userID, String type, String firstName, String lastName, String username, String password, String email, String DOB, ArrayList<Course> currentCourses, double grades) {
        setUUID(userID);
        setType(type);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(username);
        setPassword(password);
        setUserEmail(email);
        setUserDOB(DOB);
        setCurrentCourses(currentCourses);
        setGrades(grades);
    }

    /**
    * Overloaded constructor without grades parameter (default grades to 0.0)
    */
    public Student(UUID userID, String type, String firstName, String lastName, String username, String password, String email, String DOB, ArrayList<Course> currentCourses) {
        this(userID, type, firstName, lastName, username, password, email, DOB, currentCourses, 0.0);
    }

    /**
    * Student class for new students and sets their information as well as creating a random UUID for them.
    * @param type a String to determine if a user is a student or author.
    * @param firstName a String for the user's first name.
    * @param lastName a String for the user's last name.
    * @param username a String for the user's username for login.
    * @param password a String for the user's password.
    * @param email a String of the user's electronic mailing address.
    * @param DOB a String for the user's birth date.
    * @param currentCourses an array of the courses the use is currently enrolled in.
    * @param grades a double value of the student's grades after completing assignments
    */
    public Student(String type, String firstName, String lastName, String username, String password, String email, String DOB, ArrayList<Course> currentCourses, double grades) {
        setUUID(UUID.randomUUID());
        setType(type);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(username);
        setPassword(password);
        setUserEmail(email);
        setUserDOB(DOB);
        setCurrentCourses(currentCourses);
        setGrades(grades);
    }

    /**
    * Overloaded constructor without grades parameter (default grades to 0.0)
    */
    public Student(String type, String firstName, String lastName, String username, String password, String email, String DOB, ArrayList<Course> currentCourses) {
        this(type, firstName, lastName, username, password, email, DOB, currentCourses, 0.0);
    }

    /**
    * Student class for students that haven't enrolled in or taken courses yet.
    * @param userID a user's ID
    * @param type a String to determine if a user is a student or author.
    * @param firstName a String for the user's first name.
    * @param lastName a String for the user's last name.
    * @param username a String for the user's username for login.
    * @param password a String for the user's password.
    * @param email a String of the user's electronic mailing address.
    * @param DOB a String for the user's birth date.
    */
    public Student(UUID userID, String type, String firstName, String lastName, String username, String password, String email, String DOB) {
        setUUID(userID);
        setType(type);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(username);
        setPassword(password);
        setUserEmail(email);
        setUserDOB(DOB);
        setGrades(0.0); // Default to 0.0 for new students
    }

    /**
    * Check the grades for this student
    * @return the grades object for this student
    */
    public Grades checkGrades() {
        return null;
    }

    /**
    * gives the student a way to include a new course in the current courses they have.
    */
    public void joinCourse(Course course) {
        this.getCurrentCourses().add(course);
    }

    /**
    * uses a toString method that prints a student's personal information and registration information.
    * @return A toString of the student's UUID letting a user see their ID.
    *         A toString of the student's first name letting them see their registered first name.
    *         A toString of the student's last name letting them see their registered last name.
    *         A toString of the student's username letting them see their username.
    *         A toString of the student's password letting them see their password.
    *         A toString of the user's electronic mailing address letting them see their registered email.
    *         A toString of the user's birth date letting them see their registered date of birth.
    */
    public String toString() {
        return "Student UUID: " + this.getUUID().toString() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: " + this.getLastName() + 
        "\nUsername: " + this.getUserName() + "\nPassword: " + this.getPassword() + "\nEmail: " + this.getUserEmail() + "\nDate Of Birth: " + this.getUserDOB();
    }

}
