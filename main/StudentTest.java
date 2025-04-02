package Tank;

import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;

public class StudentTest {
    
    @Test
    public void testConstructor() {
        UUID userID = UUID.randomUUID();
        String type = "student";
        String firstName = "Aidan";
        String lastName = "Macklen";
        String username = "amacklen";
        String password = "password123";
        String email = "amacklen@gmail.com";
        String DOB = "01/24/2003";
        ArrayList<Course> currentCourses = new ArrayList<Course>();
        Student student = new Student(userID, type, firstName, lastName, username, password, email, DOB, currentCourses);

        assert student.getUUID().equals(userID);
        assert student.getType().equals(type);
        assert student.getFirstName().equals(firstName);
        assert student.getLastName().equals(lastName);
        assert student.getUserName().equals(username);
        assert student.getPassword().equals(password);
        assert student.getUserEmail().equals(email);
        assert student.getUserDOB().equals(DOB);
        assert student.getCurrentCourses().equals(currentCourses);

        Student student2 = new Student(type, firstName, lastName, username, password, email, DOB, currentCourses);
        assert student2.getType().equals(type);
        assert student2.getFirstName().equals(firstName);
        assert student2.getLastName().equals(lastName);
        assert student2.getUserName().equals(username);
        assert student2.getPassword().equals(password);
        assert student2.getUserEmail().equals(email);
        assert student2.getUserDOB().equals(DOB);
        assert student2.getCurrentCourses().equals(currentCourses);
    }

    @Test
    public void testJoinCourse() {
        UUID userID = UUID.randomUUID();
        String type = "student";
        String firstName = "Aidan";
        String lastName = "Macklen";
        String username = "amacklen";
        String password = "password123";
        String email = "amacklen@gmail.com";
        String DOB = "01/24/2003";

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
        Author author = new Author(type2, firstName2, lastName2, username2, password2, email2, DOB2);
        String difficulty = "Easy";
        String summary = "blah";

        ArrayList<Course> currentCourses = new ArrayList<Course>();

        Student student = new Student(userID, type, firstName, lastName, username, password, email, DOB, currentCourses);
        Course course = new Course(title, dateCreated, language, author, difficulty, null, summary, null, null);
        
        student.joinCourse(course);
        assert student.getCurrentCourses().contains(course);
    }

    @Test
    public void testToString() {
        UUID userID = UUID.randomUUID();
        String type = "student";
        String firstName = "Aidan";
        String lastName = "Macklen";
        String username = "amacklen";
        String password = "password123";
        String email = "amacklen@gmail.com";
        String DOB = "01/24/2003";
        ArrayList<Course> currentCourses = new ArrayList<Course>();

        Student student = new Student(userID, type, firstName, lastName, username, password, email, DOB, currentCourses);
        String expected = "Student UUID: " + userID.toString() + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + 
        "\nUsername: " + username + "\nPassword: " + password + "\nEmail: " + email + "\nDate Of Birth: " + DOB;
        
        assert student.toString().equals(expected);
    }
}
