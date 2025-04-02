package Tank;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest {
    private UserList users = UserList.getInstance();
    private ArrayList<RegisteredUser> userList = users.getUsers();
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
    void testAddingZeroUsers() {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    @Test
    void testAddingOneUser() {
        userList.add(new Student("Student", "Bob", "Smith", "Bsmith420", "BobbyIsTheMan47", "epic@coolguy.com", "05/05/05", null));
        DataWriter.saveUsers();
        assertEquals("Bsmith420", DataLoader.getUsers().get(0).getUserName());
    }

    @Test
    void testAddingThreeUsers() {
        userList.add(new Student("Student", "Bob", "Smith", "Bsmith420", "BobbyIsTheMan47", "epic@coolguy.com", "05/05/05", null));
        userList.add(new Student("Author", "Stevie", "Wonder", "TheBlindPianoGuy", "SteveWillDoIt", "piano@music.com", "02/12/48", null));
        userList.add(new Student("Student", "Kyle", "Nelk", "nelkFan69", "ILoveNelk", "college@nelk.com", "03/28/01", null));
        DataWriter.saveUsers();
        assertEquals("nelkFan69", DataLoader.getUsers().get(2).getUserName());
    }

    @Test
    void testAddingNullUser() {
        userList.add(new Student("Student", null, null, null, null, null, null, null));
        DataWriter.saveUsers();
        assertEquals(null, DataLoader.getUsers().get(0));
    }

    @Test
    void testAddingSameUserName() {
        userList.add(new Student("Student", "Bob", "Smith", "Bsmith420", "BobbyIsTheMan47", "epic@coolguy.com", "05/05/05", null));
        userList.add(new Student("Student", "Bart", "Smith", "Bsmith420", "BartIsTheMan18", "cool@epicguy.com", "02/02/02", null));
        DataWriter.saveUsers();
        assertEquals(DataLoader.getUsers().get(0).getUserName(), DataLoader.getUsers().get(1).getUserName());
    }

    @Test
    void testAddingZeroCourses() {
        courseList = DataLoader.getCourses();
        assertEquals(0, courseList.size());
    }

    @Test
    void testAddingOneCourse() {
        courseList.add(new Course("Advanced Python", "01/15/23", "Python", null, "advanced", null, "This is a course for experienced python coders who want to up their python game", null, null));
        DataWriter.saveCourses();
        assertEquals("Advanced Python", DataLoader.getCourses().get(0).getTitle());
    }

    @Test
    void testAddingThreeCourses() {
        courseList.add(new Course("Advanced Python", "01/15/23", "Python", null, "advanced", null, "This is a course for experienced python coders who want to up their python game", null, null));
        courseList.add(new Course("Intermediate C++", "04/12/19", "C++", null, "intermediate", null, "This is a course for slightly experienced C++ coders who are wishing to learn more than just the basics of C++", null, null));
        courseList.add(new Course("Beginner C++", "01/30/18", "C++", null, "beginner", null, "This is a course for completely unexperienced C++ coders who are wishing to learn the basics of C++", null, null));
        DataWriter.saveCourses();
        assertEquals("Beginner C++", DataLoader.getCourses().get(2).getTitle());
    }

    @Test
    void testAddingNullCourse() {
        courseList.add(new Course(null, null, null, null, null, null, null, null, null));
        DataWriter.saveCourses();
        assertEquals(null, DataLoader.getCourses().get(0));
    }

    @Test
    void testAddingSameTitle() {
        courseList.add(new Course("Intermediate C++", "04/12/19", "C++", null, "intermediate", null, "This is a course for slightly experienced C++ coders who are wishing to learn more than just the basics of C++", null, null));
        courseList.add(new Course("Intermediate C++", "05/24/20", "C++", null, "intermediate", null, "This is a way better course for slightly experienced C++ coders who are wishing to learn more than just the basics of C++", null, null));
        DataWriter.saveCourses();
        assertEquals(DataLoader.getCourses().get(0).getTitle(), DataLoader.getCourses().get(1).getTitle());
    }
}
