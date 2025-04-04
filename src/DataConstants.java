package src;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataConstants {
    // Template files (examples)
    protected static final String USER_TEMPLATE_FILE = "usersExample.json";
    protected static final String COURSE_TEMPLATE_FILE = "coursesExample.json";
    
    // Working files (will be created)
    protected static final String USER_FILE_NAME = "json/users.json";
    protected static final String COURSE_FILE_NAME = "json/course.json";
    
    // Check if working files exist and create them from templates if they don't
    static {
        try {
            // Create json directory if it doesn't exist
            Files.createDirectories(Paths.get("json"));
            
            // Check if users.json exists, if not, copy from template
            File userFile = new File(USER_FILE_NAME);
            if (!userFile.exists()) {
                File templateFile = new File(USER_TEMPLATE_FILE);
                if (templateFile.exists()) {
                    Files.copy(templateFile.toPath(), userFile.toPath());
                } else {
                    // Create empty JSON array if template doesn't exist
                    try (FileWriter writer = new FileWriter(userFile)) {
                        writer.write("[]");
                    }
                }
            }
            
            // Check if course.json exists, if not, copy from template
            File courseFile = new File(COURSE_FILE_NAME);
            if (!courseFile.exists()) {
                File templateFile = new File(COURSE_TEMPLATE_FILE);
                if (templateFile.exists()) {
                    Files.copy(templateFile.toPath(), courseFile.toPath());
                } else {
                    // Create empty JSON array if template doesn't exist
                    try (FileWriter writer = new FileWriter(courseFile)) {
                        writer.write("[]");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error initializing data files: " + e.getMessage());
        }
    }
    
    //users
    protected static final String USER_ID = "userID";
    protected static final String USER_TYPE = "type";
    protected static final String USER_FIRST_NAME = "first_name";
    protected static final String USER_LAST_NAME = "last_name";
    protected static final String USER_USERNAME = "username";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_DOB = "DOB";


    // Courses section 
    protected static final String COURSE_ID = "authorID";
    protected static final String COURSE_TITLE = "course";
    protected static final String COURSE_DATE_CREATED = "created";
    protected static final String COURSE_LANGUAGE = "language";
    protected static final String COURSE_DIFFICULTY = "difficulty";

    //array review
    protected static final String COURSE_REVIEW = "review";
    //include userID 
    protected static final String COURSE_RATING = "rating";
    protected static final String COURSE_REVIEW_ID = "userID";
    protected static final String COURSE_COMMENTS = "comment";
    protected static final String COURSE_FIRST_NAME = "firstName";
    protected static final String COURSE_LAST_NAME = "lastName";


    protected static final String COURSE_COURSE_COMMENTS = "courseComments";
    protected static final String COURSE_SUMMARY = "summary";

    protected static final String COURSE_MODULES = "modules";
    protected static final String COURSE_MODULE_TITLE = "title";

    protected static final String COURSE_LESSONS = "lessons";
    protected static final String COURSE_LESSON_NUMBER = "lessonNumber";
    protected static final String COURSE_LESSON_TITLE = "lessonTitle";
    protected static final String COURSE_LESSON_CONTENT = "content";

    protected static final String COURSE_QUIZZES = "quiz";

    protected static final String COURSE_QUESTIONS = "quizQuestions";

    protected static final String COURSE_QUESTION_PROMPT = "question";
    protected static final String COURSE_ANSWER_A = "answerA";
    protected static final String COURSE_ANSWER_B = "answerB";
    protected static final String COURSE_ANSWER_C = "answerC";
    protected static final String COURSE_ANSWER_D = "answerD";
    protected static final String COURSE_CORRECT_ANSWER = "correctAnswer";

    protected static final String COURSE_USER_RESULTS = "userResults";
    protected static final String COURSE_USER_ID = "userID";
    protected static final String COURSE_GRADE = "grade";

}
