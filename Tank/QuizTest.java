package Tank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuizTest {
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
    public void testCreateNewQuiz() {
        ArrayList<Question> tempQuestions = new ArrayList<Question>();
        Quiz tempQuiz = new Quiz(tempQuestions);
        module.setQuiz(tempQuiz);
        assertEquals(module.getQuiz(), tempQuiz);
    }

    @Test
    public void testCreateNewQuestion() {
        ArrayList<String> tempChoices = new ArrayList<String>();
        ArrayList<Question> tempQuestions = new ArrayList<Question>();
        Quiz tempQuiz = new Quiz(tempQuestions);
        tempQuiz.createQuestion("What is the programming language you are coding in for this class?", 1, tempChoices, 4);
        assertEquals(tempQuiz.getQuestions().get(0).getPrompt(), "What is the programming language you are coding in for this class?");
    }

}
