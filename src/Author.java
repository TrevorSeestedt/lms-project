package src;

import java.util.ArrayList;
import java.util.UUID;
public class Author extends RegisteredUser {

    /**
    * Sets the UUID,Type, firstName, lastName, username, password, email, and DOB of an author.
    * @param userID A user's unique ID.
    * @param type String to determine if a user is a student or author.
    * @param firstName String for the user's first name.
    * @param lastName String for the user's last name.
    * @param username String for the user's username for login.
    * @param password String for the user's password.
    * @param email String for the user's electronic mailing address.
    * @param DOB String for the user's birth date.
    */
    public Author(UUID userID, String type, String firstName, String lastName, String username, String password, String email, String DOB) {
        setUUID(userID);
        setType(type);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(username);
        setPassword(password);
        setUserEmail(email);
        setUserDOB(DOB);
    }

    /**
    * Sets the type, firstName, lastName, username, password, email, and DOB for an author. Sets a random UUID in case of a new author.
    * @param type String to determine if a user is a student or author.
    * @param firstName String for the user's first name.
    * @param lastName String for the user's last name.
    * @param username String for the user's username for login.
    * @param password String for the user's password.
    * @param email String for the user's electronic mailing address.
    * @param DOB String for the user's birth date.
    */
    public Author(String type, String firstName, String lastName, String username, String password, String email, String DOB) {
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
    * Method for this author to add a course and save the courses in DataWriter.
    * @param course The values required for a course to function.
    */
    public void addCourse(Course course) {
        this.getCurrentCourses().add(course);
        DataWriter.saveCourses();
    }

    /**
    * String to represent an author's personal information, UUID, and registration information.
    * @return A string version of the author's UUID, first name, last name, username, password, email, and date of birth.
    */
    public String toString() {
        return "Author UUID: " + this.getUUID().toString() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: " + this.getLastName() + 
        "\nUsername: " + this.getUserName() + "\nPassword: " + this.getPassword() + "\nEmail: " + this.getUserEmail() + "\nDate Of Birth: " + this.getUserDOB();
    }

    public void editModulePrompt(Module module, String newPrompt) {
        module.setPrompt(newPrompt);
    }

    public void editModuleQuiz(Module module, Quiz quiz) {
        module.setQuiz(quiz);
    }

    public void editModuleLesson(Module module, Lesson lesson) {
        for(int i=0;i<module.getLessons().size();++i) {
            if(module.getLessons().get(i).getLessonNumber() == lesson.getLessonNumber()) {
                module.removeLesson(module.getLessons().get(i));
                module.addLesson(lesson);
                break;
            }
        }
    }

}
