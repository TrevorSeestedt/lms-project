package src;

import java.util.ArrayList;
import java.util.UUID;

public abstract class RegisteredUser {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UUID userID;
    private String userType;
    private ArrayList<Course> currentCourses = new ArrayList<Course>();
    private String DOB;
    private String userEmail;
    private double grades;

    public RegisteredUser() {

    }
    // constructor without grades and courses being passed in
    public RegisteredUser(String type, String firstName, String lastName, String username, String password, String email, String DOB) {
        setUUID(UUID.randomUUID());
        setType(type);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(username);
        setPassword(password);
        setUserEmail(email);
        setUserDOB(DOB);
    }
    /**
    * @param userType String determining whether the user is a student or author.
    * @param firstName Legal first name of the user.
    * @param lastName Legal last name of the user.
    * @param username name the user writes for login.
    * @param password String the user inputs to login with the given username.
    * @param userEmail the user's electronic mailing address.
    * @param DOB user's birth date.
    * @param currentCourses array that lists the courses the user is currently a part of.
    * @param grades The grades the user got from assignments.
    */
    public RegisteredUser(String userType, String firstName, String lastName, String username, String password, String userEmail, String DOB,
    ArrayList<Course> currentCourses, double grades) {
        setUUID(UUID.randomUUID());
        setType(userType);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(username);
        setPassword(password);
        setUserEmail(userEmail);
        setUserDOB(DOB);
        setCurrentCourses(currentCourses);
        setGrades(grades);
    }
    
    /**
    * Method that sets the required parameters a user needs to 
    * @param userType is determinate of whether a user is a student or author.
    * @param firstName a String for the user's first name.
    * @param lastName a String for the user's last name.
    * @param username a String for the user's username for login.
    * @param password a String for the user's password.
    * @param email a String of the user's electronic mailing address.
    * @param DOB a String for the user's birth date.
    * @param currentCourses the courses the user is currently a part of.
    * @param grades the grades the user got from assignments.
    */
    public RegisteredUser(UUID userID, String userType, String firstName, String lastName, String username, String password, String email, String DOB, 
    ArrayList<Course> currentCourses, double grades) {
        setUUID(userID);
        setType(userType);
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
    * boolean method for login
    * @return false boolean for the login of a user.
    */
    public boolean login() {
        return false;
    }

    /**
    * boolean method for a user loggin out.
    * @return true boolean for a user that is logging out.
    */
    public boolean logout() {
        DataWriter.saveUsers();
        return true;
    }

    /**
    * adds a course to a registered user's current courses.
    */
    public void addCourse(Course course) {
        currentCourses.add(course);
    }

    /**
    * removes a course from a registered user's current courses.
    */
    public void removeCourse(Course course) {
        currentCourses.remove(course);
    }

    /**
    * Method to be able to add a new review for a course.
    * @param course has the requried variables for a course to function.
    * @param rating double value a user can use to show how they feel about a course numerically.
    * @param comment a String a user uses to write out their views on the course.
    */
    public void addReview(Course course, double rating, String comment) {
        Review newReview = new Review(rating, comment, this, course);
        course.addReview(newReview);
    }

    /**
    * capable of removing a review that was done for a course
    * @param course stores the parameters for a Course.
    * @param review contains values for a review given to a course.
    */
    public void removeReview(Course course, Review review) {
        course.removeReview(review);
    }

    /**
    * Allows the code access to the information in firstName.
    * @return the legal first name of the user.
    */
    public String getFirstName() {
        return this.firstName;
    }

    /**
    * Allows the code to change the information in firstName.
    */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
    * Allows the code to access information in lastName.
    * @return the legal last name of the user.
    */
    public String getLastName() {
        return this.lastName;
    }

    /**
    * Allows the program to change the information in lastName.
    */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
    * Allows the program to access the information in userID.
    * @return gets the information of userID.
    */
    public UUID getUUID() {
        return this.userID;
    }

    /**
    * Lets the program to change what's in userID.
    */
    public void setUUID(UUID userID) {
        this.userID = userID;
    }

    /**
    * Lets the program access the information in DOB.
    * @return gets the information in DOB.
    */
    public String getUserDOB() {
        return this.DOB;
    }

    /**
    * Allows the program to change what's in DOB.
    */
    public void setUserDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
    * Allows the program access to information in userType.
    * @return gets the information in userType.
    */
    public String getType() {
        return this.userType;
    }

    /**
    * Allows the program to change what's in userType.
    */
    public void setType(String userType) {
        this.userType = userType;
    }

    /**
    * Allows the program access to information in username.
    * @return gets the information in username.
    */
    public String getUserName() {
        return this.username;
    }

    /**
    * Allows the program to change what's in username.
    */
    public void setUserName(String username) {
        this.username = username;
    }

    /**
    * Allows the program to access information in password.
    * @return accesss the information in password
    */
    public String getPassword() {
        return this.password;
    }

    /**
    * Allows the program to change what's in password.
    */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
    * Allows the program to access what's in userEmail.
    * @return accesses userEmail for the method.
    */
    public String getUserEmail() {
        return this.userEmail;
    }

    /**
    * Allows the program to change what's in userEmail.
    */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
    *Allows the program access to what's in grades.
    * @return accesses grades for the method.
    */
    public double getGrades() {
        return this.grades;
    }

    /**
    * Allows the program to change the values in grades.'
    */
    public void setGrades(double grades) {
        this.grades = grades;
    }

    /**
    * Allows the program to access information in currentCourses.
    * @return accesses currentCourses for the method.
    */
    public ArrayList<Course> getCurrentCourses() {
        return this.currentCourses;
    }

    /**
    * Allows the program to change the courses a user is currently a part of.
    * @param currentCourses courses a user is currently a part of.
    */
    public void setCurrentCourses(ArrayList<Course> currentCourses) {
        this.currentCourses = currentCourses;
    }

    public Course getCourseByCourseTitle(String courseTitle) {
        for(Course course : currentCourses) {
            if(course.getTitle().equals(courseTitle)) {
                return course;
            }
        }
        return null;
    }
}
