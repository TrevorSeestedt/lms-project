package Tank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorTest {
    private UserList users = UserList.getInstance();
    private ArrayList<RegisteredUser> userList = users.getUsers();
    
    private Course course = new Course();
    private Module module = new Module();
    private CourseList courses = CourseList.getInstance();
    private ArrayList<Course> courseList = courses.getCourses();
    


    @BeforeEach
    public void setup() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
        CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
        CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses();
    }

    @Test
    public void testAddNewAuthor() {
        Author author = new Author("uuid", "type", "firstname", 
        "lastname", "password","email","dob");
        userList.add(author);
        assertEquals(users.getUsers().get(0), author);
    }

    @Test
    public void testEditModulePrompt() {
        Author author = new Author("uuid", "type", "firstname", 
        "lastname", "password","email","dob");
        ArrayList<Lesson> tempLessons = new ArrayList<Lesson>();
        Quiz tempQuiz = new Quiz();
        Module tempModule = new Module("Test Module", tempLessons, tempQuiz);
        author.editModulePrompt(tempModule, "this is a brand new module prompt");
        assertEquals(module.getPrompt(), "this is a brand new module prompt");

    }

    @Test
    public void testEditModuleQuiz() {
        Author author = new Author("uuid", "type", "firstname", 
        "lastname", "password","email","dob");
        
        ArrayList<Lesson> tempLessons = new ArrayList<Lesson>();
        Quiz tempQuiz = new Quiz();
        Module tempModule = new Module("Test Module", tempLessons, tempQuiz);
        author.editModuleQuiz(tempModule, tempQuiz);
        assertEquals(course.getModules().get(0).getQuiz(), tempQuiz);
    }

    @Test
    public void testEditModuleLesson() {
        Author author = new Author("uuid", "type", "firstname", 
        "lastname", "password","email","dob");
        
        ArrayList<Lesson> tempLessons = new ArrayList<Lesson>();
        Quiz tempQuiz = new Quiz();
        Module tempModule = new Module("Test Module", tempLessons, tempQuiz);
        Lesson tempLesson = new Lesson("Lesson about for loops", "For loops are awesome", "1.1");
        author.editModuleLesson(tempModule, tempLesson);
        assertEquals(course.getModules().get(0).getLessons().get(0), tempLesson);
    }


}
