package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
    /**
     * Method adds the current list of users to a list of json users and saves the list of users to a .json file
     */
    public static void saveUsers() {
        UserList users = UserList.getInstance();
        ArrayList<RegisteredUser> registeredUsers = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        // create JSON objects
        for(int i=0;i<registeredUsers.size();i++) {
            jsonUsers.add(getUserJSON(registeredUsers.get(i)));
        }

        // write JSON file
        try (FileWriter file = new FileWriter((USER_FILE_NAME))) {

            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method puts all of the details from a user json file into a JSON Object and returns that object
     * @param user
     * @return JSONObject userDetails
     */
    public static JSONObject getUserJSON(RegisteredUser user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_ID, user.getUUID().toString());
        userDetails.put(USER_TYPE, user.getType());
        userDetails.put(USER_USERNAME, user.getUserName());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_EMAIL, user.getUserEmail());
        userDetails.put(USER_DOB, user.getUserDOB());

        return userDetails;
    }
    /**
     * Method adds the current list of Courses to a list of json courses and saves the list of courses to a .json file
     */
    public static void saveCourses() {
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> registeredCourses = courses.getCourses();
        JSONArray jsonCourses = new JSONArray();

        // create JSON objects
        for(int i=0;i<registeredCourses.size();++i) {
            jsonCourses.add(getCourseJSON(registeredCourses.get(i)));
        }

        // write JSON file
        try(FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
            file.write(jsonCourses.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Method adds all of the details found in a course json file sets them equal to the appropriate data constants and then writes them to a json file
     * @param course
     * @return JSONObject courseDetails
     */
    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        JSONArray jsonQuestionArray = new JSONArray();
        JSONArray jsonLessonArray = new JSONArray();
        JSONArray jsonCourseReviewsArray = new JSONArray();

        courseDetails.put(COURSE_ID, course.getCourseID().toString());
        courseDetails.put(COURSE_TITLE, course.getTitle());
        courseDetails.put(COURSE_DATE_CREATED, course.getDateCreated());
        courseDetails.put(COURSE_LANGUAGE, course.getLanguage());
        courseDetails.put(COURSE_DIFFICULTY, course.getDifficulty());
        courseDetails.put(COURSE_SUMMARY, course.getSummary());
        
        // loop to add all reviews
        for(int i=0;i<course.getReviews().size();++i) {
            JSONObject jsonCourseReview = new JSONObject();
            Review tempReview = course.getReviews().get(i);

            jsonCourseReview.put(COURSE_REVIEW_ID, tempReview.getUUID().toString()); 
            jsonCourseReview.put(COURSE_RATING, tempReview.getRating2());
            jsonCourseReview.put(COURSE_COMMENTS, tempReview.getComment());
            jsonCourseReview.put(COURSE_FIRST_NAME, tempReview.getFirstName());
            jsonCourseReview.put(COURSE_LAST_NAME, tempReview.getLastName());
            jsonCourseReviewsArray.add(jsonCourseReview);
        }
        courseDetails.put(COURSE_REVIEW, jsonCourseReviewsArray);
        
        // looping thru to add all modules
        JSONArray jsonCourseModuleArray = new JSONArray();
        for(int i=0;i<course.getModules().size();++i) {
            JSONObject jsonCourseModule = new JSONObject();
            Module tempModule = course.getModules().get(i);
            
            jsonCourseModule.put(COURSE_MODULE_TITLE, tempModule.getTitle());
            
            // Add lessons for this module
            JSONArray jsonModuleLessons = new JSONArray();
            for(int j=0;j<tempModule.getLessons().size();++j) {
                Lesson tempLesson = tempModule.getLessons().get(j);
                JSONObject jsonCourseLesson = new JSONObject();
                jsonCourseLesson.put(COURSE_LESSON_TITLE, tempLesson.getTitle());
                jsonCourseLesson.put(COURSE_LESSON_CONTENT, tempLesson.getContents());
                jsonCourseLesson.put(COURSE_LESSON_NUMBER, tempLesson.getLessonNumber());
                jsonModuleLessons.add(jsonCourseLesson);
            }
            jsonCourseModule.put(COURSE_LESSONS, jsonModuleLessons);
            
            // Add quiz for this module
            JSONObject jsonCourseQuiz = new JSONObject();
            Quiz tempQuiz = tempModule.getQuiz();
            JSONArray jsonModuleQuestions = new JSONArray();
            for(int k=0;k<tempQuiz.getQuestions().size();++k) {
                Question tempQuestion = tempQuiz.getQuestions().get(k);
                JSONObject jsonCourseQuestion = new JSONObject();
                jsonCourseQuestion.put(COURSE_QUESTION_PROMPT, tempQuestion.getPrompt());
                jsonCourseQuestion.put(COURSE_ANSWER_A, tempQuestion.getChoices().get(0));
                jsonCourseQuestion.put(COURSE_ANSWER_B, tempQuestion.getChoices().get(1));
                jsonCourseQuestion.put(COURSE_ANSWER_C, tempQuestion.getChoices().get(2));
                jsonCourseQuestion.put(COURSE_ANSWER_D, tempQuestion.getChoices().get(3));
                jsonCourseQuestion.put(COURSE_CORRECT_ANSWER, tempQuestion.getCorrectAnswer());
                jsonModuleQuestions.add(jsonCourseQuestion);
            }
            jsonCourseQuiz.put(COURSE_QUESTIONS, jsonModuleQuestions);
            jsonCourseModule.put(COURSE_QUIZZES, jsonCourseQuiz);
            jsonCourseModuleArray.add(jsonCourseModule);
        }
        courseDetails.put(COURSE_MODULES, jsonCourseModuleArray);

        // loop to add userResults and everything with userResults
        JSONArray jsonCourseUserResultsArray = new JSONArray();
        for(int i=0;i<course.getUserResults().size();++i) {
            JSONObject jsonCourseUserResults = new JSONObject();
            Results tempResults = course.getUserResults().get(i);
            jsonCourseUserResults.put(COURSE_USER_ID, tempResults.getUUID().toString());
            
            // Store grades directly as an array
            JSONArray gradesArray = new JSONArray();
            ArrayList<Double> tempGrades = tempResults.getGrades();
            for(int j=0;j<tempGrades.size();++j) {
                gradesArray.add(tempGrades.get(j));
            }
            jsonCourseUserResults.put(COURSE_GRADE, gradesArray);
            
            jsonCourseUserResultsArray.add(jsonCourseUserResults);
        }
        courseDetails.put(COURSE_USER_RESULTS, jsonCourseUserResultsArray);
        
        return courseDetails;
    }
}
