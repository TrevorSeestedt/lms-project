package Tank;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants{
    //users section 
    public static ArrayList<RegisteredUser> getUsers() {
        ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < usersJSON.size(); i++) {
                JSONObject registeredUserJSON = (JSONObject)usersJSON.get(i); 
                UUID userID = UUID.fromString((String)registeredUserJSON.get(USER_ID));
                String type = (String)registeredUserJSON.get(USER_TYPE);
                String firstName = (String)registeredUserJSON.get(USER_FIRST_NAME);
                String lastName = (String)registeredUserJSON.get(USER_LAST_NAME);
                String username = (String)registeredUserJSON.get(USER_USERNAME);
                String password = (String)registeredUserJSON.get(USER_PASSWORD);
                String email = (String)registeredUserJSON.get(USER_EMAIL);
                String DOB = (String)registeredUserJSON.get(USER_DOB);

                if (type.equalsIgnoreCase("author")) {
                    users.add(new Author(userID, type, firstName, lastName, username, password, email, DOB));
                }
                else if (type.equalsIgnoreCase("student")) {
                    users.add(new Student(userID, type, firstName, lastName, username, password, email, DOB)); 
                }            
                
            } 
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    //courses section 
    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i=0; i < coursesJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) coursesJSON.get(i);

                UUID courseID = UUID.fromString((String)courseJSON.get(COURSE_ID));
                String courseTitle = (String)courseJSON.get(COURSE_TITLE);
                String courseCreated =(String)courseJSON.get(COURSE_DATE_CREATED);
                String courseLanguage = (String)courseJSON.get(COURSE_LANGUAGE);

                Author courseAuthor = (Author)UserList.getInstance().getUserByUUID(courseID);

                String courseDifficulty = (String)courseJSON.get(COURSE_DIFFICULTY);
                
                //parse review 
                JSONArray reviewJSON = (JSONArray) courseJSON.get(COURSE_REVIEW);
                ArrayList<Review> reviews = new ArrayList<>();
                for (int j = 0; j < reviewJSON.size(); j++) {
                    JSONObject reviewsJSON = (JSONObject) reviewJSON.get(j);
                    UUID userID = UUID.fromString((String)reviewsJSON.get(USER_ID));
                    String courseRating = (String)reviewsJSON.get(COURSE_RATING);
                    String courseComment = (String)reviewsJSON.get(COURSE_COMMENTS);
                    String courseFirstName = (String)reviewsJSON.get(COURSE_FIRST_NAME);
                    String courseLastName = (String)reviewsJSON.get(COURSE_LAST_NAME);
                    Review review = new Review(courseRating, courseComment, userID, courseFirstName, courseLastName); 
                    reviews.add(review);
                }

                String courseSummary = (String)courseJSON.get(COURSE_SUMMARY);
                
                //parse modules 
                JSONArray moduleJSON = (JSONArray) courseJSON.get(COURSE_MODULES);
                ArrayList<Module> modules = new ArrayList<>();
                for (int j = 0; j < moduleJSON.size(); j++) {
                    JSONObject modulesJSON = (JSONObject) moduleJSON.get(j);
                    String moduleTitle = (String) modulesJSON.get(COURSE_MODULE_TITLE);

                    //parse lessons
                    JSONArray lessonJSON = (JSONArray) modulesJSON.get(COURSE_LESSONS);
                    ArrayList<Lesson> lessons = new ArrayList<>();
                    for (int k = 0; k < lessonJSON.size(); k++) {
                        JSONObject lessonsJSON = (JSONObject) lessonJSON.get(k);
                        
                        String courseLessonNumber = (String) lessonsJSON.get(COURSE_LESSON_NUMBER);
                        String courseLessonTitle = (String) lessonsJSON.get(COURSE_LESSON_TITLE);
                        String courseLessonContent = (String) lessonsJSON.get(COURSE_LESSON_CONTENT);

                        Lesson lesson = new Lesson(courseLessonTitle, courseLessonContent, courseLessonNumber);   
                        lessons.add(lesson);
                    }
                    //change here

                    JSONObject quizJSON = (JSONObject) modulesJSON.get(COURSE_QUIZZES);
                    
                    //choices for user. 
                    JSONArray questionJSON = (JSONArray) quizJSON.get(COURSE_QUESTIONS);

                    ArrayList<Question> questions = new ArrayList<>();
                    for (int l = 0; l < questionJSON.size(); l++) {
                        JSONObject questionsJSON = (JSONObject) questionJSON.get(l);
                        
                        String courseQuestion  = (String) questionsJSON.get(COURSE_QUESTION_PROMPT);

                        ArrayList<String> choices = new ArrayList<>();
                        String courseAnswerA = (String) questionsJSON.get(COURSE_ANSWER_A);
                        String courseAnswerB = (String) questionsJSON.get(COURSE_ANSWER_B);
                        String courseAnswerC = (String) questionsJSON.get(COURSE_ANSWER_C);
                        String courseAnswerD = (String) questionsJSON.get(COURSE_ANSWER_D);
                        String correctAnswerNumber = (String) questionsJSON.get(COURSE_CORRECT_ANSWER);
                        int courseCorrectAnswer = Integer.parseInt(correctAnswerNumber);

                        //manually adding course answers to array list choices
                        choices.add(courseAnswerA);
                        choices.add(courseAnswerB);
                        choices.add(courseAnswerC);
                        choices.add(courseAnswerD);

                        Question question = new Question(courseQuestion, courseCorrectAnswer, choices, 4);
                        questions.add(question);
                    }
                    Quiz quiz = new Quiz(questions);  
                    
                
                    Module module = new Module(moduleTitle, lessons, quiz);
                    modules.add(module);
                }
                JSONArray resultJSON = (JSONArray) courseJSON.get(COURSE_USER_RESULTS);
                ArrayList<Results> results = new ArrayList<>();

                for (int j = 0; j < resultJSON.size(); j++) {
                    JSONObject resultsJSON = (JSONObject) resultJSON.get(j);
                    UUID userID = UUID.fromString((String)resultsJSON.get(USER_ID));

                    //parse through grade
                    ArrayList<Double> grades = (ArrayList<Double>)resultsJSON.get(COURSE_GRADE);

                    Results result = new Results(userID, grades);
                    results.add(result);
                    
                }
                Course course = new Course(courseID, courseTitle, courseCreated, courseLanguage, courseAuthor, courseDifficulty, reviews, courseSummary, modules, results);
                courses.add(course);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
    //checker
    public static void main(String[] args) {
        ArrayList<RegisteredUser> users = DataLoader.getUsers();

        for (RegisteredUser user : users) {
            System.out.println("User ID: " + user.getUUID());
            System.out.println("Type: " + user.getType());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Username: " + user.getUserName());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Email: " + user.getUserEmail());
            System.out.println("Date of Birth: " + user.getUserDOB());
            System.out.println();
        }

        ArrayList<Course> courses = DataLoader.getCourses(); 

        for (Course course : courses) {
            System.out.println("Course ID: "+ course.getCourseID());
            System.out.println("Course Title: "+ course.getTitle());
            System.out.println("Course Date Created: "+ course.getDateCreated());
            System.out.println("Course Language: "+ course.getLanguage());
            System.out.println("Course Author: "+ course.getAuthor());
            System.out.println("Course Difficulty: "+ course.getDifficulty());
            System.out.println("Course Summary: "+ course.getSummary());
            System.out.println("Course Title: "+ course.getTitle());
            
            for (int i = 0; i < course.getModules().size(); i++) {
                course.getModules().get(i);
                System.out.println("\nLessons: ");
                for (int j = 0; j < course.getModules().get(i).getLessons().size(); j++) {
                    System.out.println(course.getModules().get(i).getLessons().get(j).toString());
                }
            }
            for (int i = 0; i < course.getModules().size();i++) {
                course.getModules().get(i);
                System.out.println("\nQuestions: ");
                for (int j = 0; j < course.getModules().get(i).getQuiz().getQuestions().size(); j++) {
                    System.out.println(course.getModules().get(i).getQuiz().getQuestions().get(j).toString());
                }
            }
            for (int i = 0; i < course.getReviews().size(); i++) {
                course.getReviews().get(i);
                System.out.println("\nReviews: ");
                System.out.println(course.getReviews().get(i).toString());
            }
            for (int i = 0; i < course.getUserResults().size();i++) {
                course.getUserResults().get(i);
                System.out.println("\nUser Results: ");
                System.out.println(course.getUserResults().get(i).toString());
            }
        }

        //username variable = "john";
        //password variable = "cox";
        //CourseUI.run();
    }  
}