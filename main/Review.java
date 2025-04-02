package Tank;

import java.util.UUID;

public class Review {
    private RegisteredUser user;
    private Course course;
    private String comment;
    private double rating;
    private String rating2; 
    private String firstname;
    private String lastname;
    private UUID userID;

    /**
    * Method which sets the rating, comment, which user is writing the review, and what course is being rated.
    * @param rating double value to show how someone views the quality of the course.
    * @param comment a String to put user thoughts about a course into words.
    * @param user has the user's registration information, UUID, and the information of if they are a student or author.
    * @param course stores the parameters for a Course.
    */
    public Review(double rating, String comment, RegisteredUser user, Course course) {
        setRating(rating);
        setComment(comment);
        setUser(user);
        setCourse(course);
    }

    /**
    * Method which sets the rating, comment, and the user that reviewed.
    * @param rating double value that shows how someone views the quality of the course
    * @param comment a String to put user thoughts about a course into words.
    * @param user has the user's registration information, UUID, and the information of if they are a student or author.
    */
    public Review(String rating, String comment, UUID userID, String firstname, String lastname) {
        setRating2(rating);
        setComment(comment);
        setUUID(userID);
        setFirstName(firstname);
        setLastName(lastname);
    }

    /**
    *
    * @param courseRating
    * @param courseComment
    * @param userID
    */
    public Review(String courseRating, String courseComment, UUID userID) {
    }

    /**
    * Method to get user to be called.
    * @return user the values of a user's registration information, UUID, and information of if they are a student or author.
    */
    public RegisteredUser getUser() {
        return this.user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    
    /**
    * Accessor method to get the course values.
    * @return course stores all of the values for courses
    */
    public Course getCourse() {
        return this.course;
    }

    /**
    * Mutator method to set the value of course.
    */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
    * String method to get the comment made
    * @return comment String that the user uses to put their thoughts about a course into words.
    */
    public String getComment() {
        return this.comment;
    }

    /**
    * Method to store a comment that's made.
    */
    public void setComment(String comment) {
        this.comment = comment; 
    }

    /**
    * Method that gets a rating made by a user
    * @return it returns the current value of what is in rating.
    */
    public double getRating() {
        return this.rating;
    }

    /**
    * Mutator that sets the value of a user's rating.
    * @param rating double value that represents how a user feels about a course.
    */
    public void setRating(double rating) {
        course.setRating(rating);
    }

    /**
    * toString to list the name of a course, how it was rated, and what comments was made by a user.
    * @return String of the title of the course
    *         String of the rating the course was given by a user.
    *         String of the comments made by the user.
    */

    public String getRating2() {
        return this.rating2;
    }

    public void setRating2(String rating) {
        this.rating2 = rating;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastname) { 
        this.lastname = lastname;
    }

    public UUID getUUID() {
        return this.userID;
    }

    public void setUUID(UUID userID) {
        this.userID = userID;
    }



    public String toString() {
        return "\nRating: " + this.getRating2() + "\nUser Comments: " + this.getComment() + "\nUser ID: " + this.getUUID() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: "+ this.getLastName();
    }
}