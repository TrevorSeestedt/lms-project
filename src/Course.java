package src;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private String title;
    private UUID courseID;
    private String dateCreated;
    private String language;
    private String summary;
    private Author author;
    private double rating;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private ArrayList<String> comments = new ArrayList<String>();
    private ArrayList<Module> modules = new ArrayList<Module>();
    private double progression;
    private String difficulty;
    private Grades grade;
    private ArrayList<Double> quizGrades = new ArrayList<Double>();
    private ArrayList<Results> userResults = new ArrayList<Results>();


    public Course() {

    }
    /**
    * Method sets all of the details for a course. Randomized UUID for an author newly registering.
    * @param title name of this course.
    * @param dateCreated date this course was created.
    * @param language language this course is written in.
    * @param author creator of the course.
    * @param difficulty how hard the course is to complete.
    * @param reviews list of reviews made for this course.
    * @param summary description of this course.
    * @param modules list of modules within this course.
    * @param userResults list of grades a user has for this course.
    */
    public Course(String title, String dateCreated, String language, Author author, String difficulty, ArrayList<Review> reviews,
    String summary, ArrayList<Module> modules, ArrayList<Results> userResults) {
        setCourseID(UUID.randomUUID());
        setTitle(title);
        setDateCreated(dateCreated);
        setLanguage(language);
        setAuthor(author);
        setDifficulty(difficulty);
        setUserResults(userResults);
        setSummary(summary);
        setModules(modules);
        setReviews(reviews);
    }

    /**
    * Method sets all of the details for a course.
    * @param courseID UUID of this course.
    * @param title name of this course.
    * @param dateCreated date the course was made.
    * @param language language the course is written in.
    * @param author Tje creator of the course.
    * @param difficulty How hard the course is
    * @param reviews List of reviews about this course.
    * @param summary summary of a created course.
    * @param modules created modules for this course.
    * @param userResults the UUID of the student and their list of grades for this course.
    */
    public Course(UUID courseID, String title, String dateCreated, String language, Author author, String difficulty, ArrayList<Review> reviews,
    String summary, ArrayList<Module> modules, ArrayList<Results> userResults) {
        setCourseID(courseID);
        setTitle(title);
        setDateCreated(dateCreated);
        setLanguage(language);
        setAuthor(author);
        setDifficulty(difficulty);
        setUserResults(userResults);
        setSummary(summary);
        setModules(modules);
        setReviews(reviews);
    }
    
    /**
    * Gets the title for this course.
    * @return acesses the title for this course.
    */
    public String getTitle() {
        return this.title;
    }
    
    /**
    * Sets the title for this course.
    * @param title name of this course.
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
    * Gets the courseID for this course.
    * @return aceses the ID for this course.
    */
    public UUID getCourseID() {
        return this.courseID;
    }

    /**
    * Sets the course ID for this course.
    * @param courseID UUID connected to this course.
    */
    public void setCourseID(UUID courseID) {
        this.courseID = courseID;
    }

    /**
    * Gets the date created for this course
    * @return acesses the date this course was created.
    */
    public String getDateCreated() {
        return this.dateCreated;
    }

    /**
    * Sets the date created for this course.
    * @param dateCreated String for the date the course was made.
    */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
    * Gets the language for this course.
    * @return
    */
    public String getLanguage() {
        return this.language;
    }

    /**
    * Sets the language for this course.
    * @param language allows for mutation of the language used in this course.
    */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
    * Gets the author for this course.
    * @return acesses the user type author.
    */
    public Author getAuthor() {
        return this.author;
    }

    /**
    * Sets the author of this course.
    * @param type of user with permissions to create courses.
    */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
    * Gets the summary of this course.
    * @return Acesses the summary of this course.
    */
    public String getSummary() {
        return this.summary;
    }

    /**
    * Sets the summary for this course.
    * @param summary description of this course.
    */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
    * Gets the list of modules in this course.
    * @return accesses the list of modules created for this course.
    */
    public ArrayList<Module> getModules() {
        return modules;
    }

    /**
    * Sets the list of modules in this course.
    * @param modules list of different modules created for this course.
    */
    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    /**
    * Adds a module in this course.
    * @param module A module that had been created for this course.
    */
    public void addModule(Module module) {
        modules.add(module);
    }

    public Module getCourseByModulePrompt(String modulePrompt) {
        for(Module module : modules) {
            if(module.getTitle().equals(modulePrompt)) {
                return module;
            }
        }
        return null;
    }

    /**
    * Gets the list of reviews for this course.
    * @return acesses the list of different assessments made about this course.
    */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
    * Adds a review in this course.
    * @param review The assessment given about this course.
    */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
    * Sets the list of reviews in this course.
    * @param reviews The list of different assessments made about this course.
    */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
    * Removes a user review in this course.
    * @param review The assessment given about the course.
    */
    public void removeReview(Review review) {
        reviews.remove(review);
    }

    /**
    * Gets the commments in this course
    * @return acesses the list of different comments written about this course
    */
    public ArrayList<String> getComments() {
        return comments;
    }

    /**
    * Adds a comment for this course.
    * @param comment the string added to add thoughts about this course.
    */
    public void addComment(String comment) {
        comments.add(comment);
    }

    /**
    * Gets the difficulty of this course
    * @return acesses the difficulty for this course.
    */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
    * Sets the difficulty of this course
    * @param difficulty The string for how hard a course is supposed to be.
    */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
    * Adds a comment with a specified courseID
    * @param comment The text of the comment to add
    * @param courseID The ID of the course to which the comment belongs
    */
    public void addComment(String comment, String courseID) {
        if (courseID != null && courseID.equals(this.courseID.toString())) {
            comments.add(comment);
        }
    }

    /**
    * Gets the progression of this course.
    * @return The double value for the amount of progression in this course.
    */
    public double getProgression() {
        return this.progression;
    }

    /**
    * Sets the progression of this course.
    * @param progression The numerical value given for the amount of progress in this course.
    */
    public void setProgression(double progression) {
        this.progression = progression;
    }

    /**
    * Gets the rating for this course
    * @return accesses the double value rating for this course.
    */
    public double getRating() {
        return this.rating;
    }

    /**
    * Sets the rating for this course.
    * @param rating The numerical evaluation made for this course.
    */
    public void setRating(double rating) {
        if(rating >= 0 && rating <= 5) // rating must be in between 0 and 5
            this.rating = rating;
    }

    /**
    * Gets the grade for this course.
    * @return accesses the double grade for this course.
    */
    public Grades getGrade() {
        return this.grade;
    }

    /**
    * Sets the grade for this course
    * @param grade the double value to assess a student's level of success.
    */
    public void setGrade(Grades grade) {
        this.grade = grade;
    }

    /**
    * Gets the quiz grades for this course.
    * @return acesses the quiz grades for this course.
    */
    public ArrayList<Double> getQuizGrades() {
        return this.quizGrades;
    }

    /**
    * Adds a quiz grade to the list of quiz grades for this course.
    * @param quizGrade The numerical grade a user got on a quiz.
    */
    public void addQuizGrade(double quizGrade) {
        quizGrades.add(quizGrade);
    }

    /**
    * Gets the userResults for this course.
    * @return acesses the userResults for this course.
    */
    public ArrayList<Results> getUserResults() {
        return this.userResults;
    }

    /**
    * Sets the userResults.
    * @param userResults The sucess or failer of a user.
    */
    public void setUserResults(ArrayList<Results> userResults) {
        this.userResults = userResults;
    }

    public String toString() {
        return "Course Title: " + this.getTitle() + "\nCourse Summary: " + this.getSummary() + "\nCourse Difficulty: " + this.getDifficulty();
    }
}
