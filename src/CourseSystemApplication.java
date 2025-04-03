package src;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

public class CourseSystemApplication {
    private ArrayList<Course> myCourses = new ArrayList<Course>();
    private RegisteredUser currentUser = null;
    private UserList userList = UserList.getInstance();
    Scanner input = new Scanner(System.in);

    /**
    * Method checks if the user's username is within the userlist, if it is, then it returns true. If not, it returns false.
    * @param username Login name belonging to the user.
    * @param password String required to confirmr the username belongs to the use who tries to login with it.
    * @return true if login successful, false otherwise
    */
    public boolean login(String username, String password) {
        boolean ret = false;
        for(RegisteredUser u : userList.getUsers()) {
            if(u.getUserName().equals(username) && u.getPassword().equals(password)) {
                setCurrentUser(u);
                ret = true;
            }
        }
        return ret;
    }

    /**
    * The method Gets an instance of the required parameters to sign up and saves a user using DataWriter.
    * @param userID UUID that is connected to this user.
    * @param type Determines whether a user is a student or author.
    * @param firstName The first name of this user.
    * @param lastName The last name of this user.
    * @param username The uesername the user uses to login.
    * @param password The password the user uses to login.
    * @param email The electronic mailing address of the user.
    * @param DOB The date the user was born.
    */
    public void signUp(UUID userID, String type, String firstName, String lastName, String username, String password, String email, String DOB) {
        UserList.getInstance().addUser(userID, type, firstName, lastName, username, password, email, DOB, myCourses);
        DataWriter.saveUsers();
    }

    /**
    * loops through the course array list and prints out the grade for each course until the int i equals the maximum index of the array.
    * @param grades the double value of grades the user has gotten.
    */
    public void viewGrades(Grades grades) {
        for(int i=0;i<myCourses.size();++i) {
            System.out.println(myCourses.get(i).getGrade().toString());
        }
    }

    public ArrayList<Double> viewGrades2(Course course) {
        ArrayList<Double> tempGrades = new ArrayList<Double>();
        for(int i=0;i<course.getModules().size();++i) {
            Module tempModule = course.getModules().get(i);
            tempGrades.add(tempModule.getGrade());
        }
        return tempGrades;
    }   

    /**
    * Starting at index 0, loops through and prints the index of courses until the int i is equal to the maximum index of myCourses.
    */
    public void viewMyCourses() {
        for(int i=0; i<myCourses.size(); ++i) {
            int courseNumber = i + 1;
            System.out.println(courseNumber + ". " + myCourses.get(i));
        }
    }

    /**
    * Gets an instance of the required parameters needed to add a course.
    * @param courseID A UUID that's connected to each course.
    * @param title The name of this course.
    * @param dateCreated The date this course was made.
    * @param language The language this course was made in.
    * @param author The creator of this course.
    * @param courseDifficulty How hard this course is.
    * @param review User's rating, thoughts and comments about this course.
    * @param courseSummary A description of this course.
    * @param modules A list of lessons to be used in this course.
    * @param results List of grades as well as the user's UUID for this course.
    */
    public void addCourse(UUID courseID, String title, String dateCreated, String language, Author author, String courseDifficulty, ArrayList<Review> review, String courseSummary, ArrayList<Module> modules, ArrayList<Results> results) {
        CourseList.getInstance().addCourse(courseID, title, dateCreated, language, author, courseDifficulty, review, courseSummary, modules, results);
    }

    /**
    * Method to add a review to a course.
    * @param review User's rating, thoughts and comments about this course.
    */
    public void reviewCourse(Course course, Review review) {
        course.addReview(review);
    }

    /**
    * Method to remove a review from a course.
    * @param course The modules lessons and quizes that will be used to teach a student.
    * @param review User's rating, thoughts and comments about this course.
    */
    public void removeReview(Course course, Review review) {
        course.removeReview(review);
    }

    /**
    * Method to allow user to take a quiz and calculate score
    * @param quiz A graded assignment for the user to answer a question.
    */
    public void takeQuiz(Quiz quiz) {
        int score = 0;
        
        for(int i=0; i<quiz.getQuestions().size(); i++) {
            Question question = quiz.getQuestions().get(i);
            System.out.println("Question " + (i+1) + ": " + question.getPrompt());
            
            // Display answer choices
            ArrayList<String> choices = question.getChoices();
            for (int j = 0; j < choices.size(); j++) {
                System.out.println((j+1) + ". " + choices.get(j));
            }
            
            System.out.print("Your answer (1-" + choices.size() + "): ");
            int userAnswer = -1;
            try {
                userAnswer = Integer.parseInt(input.nextLine());
                // Convert to 0-indexed
                userAnswer -= 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Skipping question.");
                continue;
            }
            
            // Check if answer is correct (answer is 0-indexed)
            if (userAnswer == question.getCorrectAnswer()) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer was: " + 
                    (question.getCorrectAnswer() + 1) + ". " + 
                    choices.get(question.getCorrectAnswer()));
            }
        }

        System.out.println("Quiz complete. Your score is " + score + " out of " + quiz.getQuestions().size() + "!");
    }

    /**
    * Method to take a module including lessons and quiz
    * @param module The module to be taken by the user
    */
    public void takeModule(Module module) {
        // Display lessons in the module
        System.out.println("Module: " + module.getTitle());
        System.out.println("\nLessons:");
        
        ArrayList<Lesson> lessons = module.getLessons();
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            System.out.println("\nLesson " + lesson.getLessonNumber() + ": " + lesson.getTitle());
            System.out.println(lesson.getContents());
            
            System.out.println("\nPress Enter to continue to the next lesson...");
            input.nextLine();
        }
        
        System.out.println("\nAll lessons completed! Now it's time for the quiz.");
        System.out.println("Press Enter to start the quiz...");
        input.nextLine();
        
        // Take the quiz for this module
        takeQuiz(module.getQuiz());
    }

    /**
    * Gets the current user in this course.
    * @return User that is currently interacting with the program.
    */
    public RegisteredUser getCurrentUser() {
        return this.currentUser;
    }

    /**
    * Sets the current user in this course
    * @param currentUser The individual that is 
    */
    public void setCurrentUser(RegisteredUser currentUser) {
        this.currentUser = currentUser;
    }
}
