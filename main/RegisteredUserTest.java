package Tank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisteredUserTest {
    private UserList users = UserList.getInstance();
    private ArrayList<RegisteredUser> userList = users.getUsers();
    
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

    @Before
    public void setup2() {
        String type2 = "author";
        String firstName2 = "Aidan";
        String lastName2 = "Macklen";
        String username2 = "amacklen";
        String password2 = "password123";
        String email2 = "amacklen@gmail.com";
        String DOB2 = "01/24/2003";
        String title = "Math";
        String dateCreated = "01/01/2020";
        String language = "Java";
        String difficulty = "Easy";
        String summary = "blah";
        Author author2 = new Author(type2, firstName2, lastName2, username2, password2, email2, DOB2);
        Course course = new Course(title, dateCreated, language, author2, difficulty, null, summary, null, null);
    }

    //add review 
    @Test
    public void testAddReview() {
        double rating = 5.0;
        String comment = "Stupendous";
        Review review = new Review(rating, comment, null, course);
        course.addReview(review);

        ArrayList<Review> reviews = course.getReviews();
        assertEquals(1, reviews.size());
        assertEquals(4.5, reviews.get(0).getRating(), 0.01);
        assertEquals("This course was great!", reviews.get(0).getComment());
    }

    //getcourebycoursetitle 
    @Test
    public void testGetCourseByCourseTitle() {
        ArrayList<Course> currentCourses = new ArrayList<>();
        currentCourses.add(course);

        Course expectedCourse = RegisteredUser.getCourseByCourseTitle("Math");
        assertNotNull(expectedCourse);
        assertEquals(course, expectedCourse);

        expectedCourse = RegisteredUser.getCourseByCourseTitle("Does not exist");
        assertNull(expectedCourse);
    }
}
