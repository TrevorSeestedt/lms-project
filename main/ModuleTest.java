package Tank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModuleTest {
    private Course course = new Course();
    private Module module = new Module();
    private CourseList courses = CourseList.getInstance();
    private ArrayList<Course> courseList = courses.getCourses();

    @BeforeEach
    public void setup() {
        CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses();
    }

    @AfterEach
    public void tearDown() {
        CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses();
    }

    @Test
    public void testCreateNewModule() {
        ArrayList<Lesson> tempLessons = new ArrayList<Lesson>();
        Quiz tempQuiz = new Quiz();
        Module tempModule = new Module("New Module 1.0", tempLessons, tempQuiz);
        course.addModule(tempModule);
        assertEquals(course.getModules().get(0), tempModule);
    }

    @Test
    public void testAddNewLesson() {
        Lesson tempLesson = new Lesson("Lesson about for loops", "For loops are awesome", "1.1");
        module.addLesson(tempLesson);
        assertEquals(module.getLessons().get(0), tempLesson);
    }

    @Test
    public void testRemoveLesson() {
        Lesson tempLesson = new Lesson("Lesson about for loops", "For loops are awesome", "1.1");
        module.addLesson(tempLesson);
        module.removeLesson(tempLesson);
        assertEquals(module.getLessons().size(), 0);
    }
}
