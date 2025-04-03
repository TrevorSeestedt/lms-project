package src;

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
        // Also set the string representation of the rating for consistency
        setRating2(String.valueOf(rating));
    }

    /**
    * Method which sets the rating, comment, and the user that reviewed.
    * @param rating string value that shows how someone views the quality of the course
    * @param comment a String to put user thoughts about a course into words.
    * @param userID the UUID of the user who created the review
    * @param firstname first name of the user
    * @param lastname last name of the user
    */
    public Review(String rating, String comment, UUID userID, String firstname, String lastname) {
        setRating2(rating);
        setComment(comment);
        setUUID(userID);
        setFirstName(firstname);
        setLastName(lastname);
        
        // Try to parse the rating as a double if possible
        try {
            double ratingValue = Double.parseDouble(rating);
            this.rating = ratingValue;
        } catch (NumberFormatException e) {
            // Default to 0.0 if parsing fails
            this.rating = 0.0;
        }
    }

    /**
    * Constructor for just rating, comment and userID
    * @param courseRating rating as a string
    * @param courseComment comment text
    * @param userID user's UUID
    */
    public Review(String courseRating, String courseComment, UUID userID) {
        setRating2(courseRating);
        setComment(courseComment);
        setUUID(userID);
        
        // Try to parse the rating as a double if possible
        try {
            double ratingValue = Double.parseDouble(courseRating);
            this.rating = ratingValue;
        } catch (NumberFormatException e) {
            // Default to 0.0 if parsing fails
            this.rating = 0.0;
        }
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
    * Method that gets a rating made by a user as a double
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
        this.rating = rating;
        if (this.course != null) {
            this.course.setRating(rating);
        }
    }

    /**
    * Gets the rating as a string
    * @return rating as a string
    */
    public String getRating2() {
        return this.rating2;
    }

    /**
    * Sets the rating as a string and attempts to update numeric rating
    * @param rating rating as a string
    */
    public void setRating2(String rating) {
        this.rating2 = rating;
        
        // Try to parse the rating as a double if possible
        try {
            double ratingValue = Double.parseDouble(rating);
            this.rating = ratingValue;
        } catch (NumberFormatException e) {
            // Keep existing value if parsing fails
        }
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
