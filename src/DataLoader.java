package src;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants{
    /**
     * Safe method to get String from JSON object
     */
    private static String getStringValue(JSONObject json, String key) {
        Object value = json.get(key);
        if (value == null) return "";
        return value.toString();
    }
    
    /**
     * Safe method to get int value from JSON object
     */
    private static int getIntValue(JSONObject json, String key, int defaultValue) {
        Object value = json.get(key);
        if (value == null) return defaultValue;
        
        if (value instanceof Long) {
            return ((Long) value).intValue();
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
    
    //users section 
    public static ArrayList<RegisteredUser> getUsers() {
        ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < usersJSON.size(); i++) {
                JSONObject registeredUserJSON = (JSONObject)usersJSON.get(i); 
                UUID userID = UUID.fromString(getStringValue(registeredUserJSON, USER_ID));
                String type = getStringValue(registeredUserJSON, USER_TYPE);
                String firstName = getStringValue(registeredUserJSON, USER_FIRST_NAME);
                String lastName = getStringValue(registeredUserJSON, USER_LAST_NAME);
                String username = getStringValue(registeredUserJSON, USER_USERNAME);
                String password = getStringValue(registeredUserJSON, USER_PASSWORD);
                String email = getStringValue(registeredUserJSON, USER_EMAIL);
                String DOB = getStringValue(registeredUserJSON, USER_DOB);

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

                UUID courseID = UUID.fromString(getStringValue(courseJSON, COURSE_ID));
                String courseTitle = getStringValue(courseJSON, COURSE_TITLE);
                String courseCreated = getStringValue(courseJSON, COURSE_DATE_CREATED);
                String courseLanguage = getStringValue(courseJSON, COURSE_LANGUAGE);

                // Use the author ID from the course data (this may need adjustment based on how authors are stored)
                Author courseAuthor = null;
                for(RegisteredUser user : UserList.getInstance().getUsers()) {
                    if(user.getType().equalsIgnoreCase("author")) {
                        courseAuthor = (Author)user;
                        break; // Use the first author for now - this should be improved to match correct author
                    }
                }

                String courseDifficulty = getStringValue(courseJSON, COURSE_DIFFICULTY);
                
                //parse review 
                JSONArray reviewJSON = (JSONArray) courseJSON.get(COURSE_REVIEW);
                ArrayList<Review> reviews = new ArrayList<>();
                for (int j = 0; j < reviewJSON.size(); j++) {
                    JSONObject reviewsJSON = (JSONObject) reviewJSON.get(j);
                    UUID userID = UUID.fromString(getStringValue(reviewsJSON, USER_ID));
                    String courseRating = getStringValue(reviewsJSON, COURSE_RATING);
                    String courseComment = getStringValue(reviewsJSON, COURSE_COMMENTS);
                    String courseFirstName = getStringValue(reviewsJSON, COURSE_FIRST_NAME);
                    String courseLastName = getStringValue(reviewsJSON, COURSE_LAST_NAME);
                    Review review = new Review(courseRating, courseComment, userID, courseFirstName, courseLastName); 
                    reviews.add(review);
                }

                String courseSummary = getStringValue(courseJSON, COURSE_SUMMARY);
                
                //parse modules 
                JSONArray moduleJSON = (JSONArray) courseJSON.get(COURSE_MODULES);
                ArrayList<Module> modules = new ArrayList<>();
                for (int j = 0; j < moduleJSON.size(); j++) {
                    JSONObject modulesJSON = (JSONObject) moduleJSON.get(j);
                    String moduleTitle = getStringValue(modulesJSON, COURSE_MODULE_TITLE);

                    //parse lessons
                    JSONArray lessonJSON = (JSONArray) modulesJSON.get(COURSE_LESSONS);
                    ArrayList<Lesson> lessons = new ArrayList<>();
                    for (int k = 0; k < lessonJSON.size(); k++) {
                        JSONObject lessonsJSON = (JSONObject) lessonJSON.get(k);
                        
                        String courseLessonNumber = getStringValue(lessonsJSON, COURSE_LESSON_NUMBER);
                        String courseLessonTitle = getStringValue(lessonsJSON, COURSE_LESSON_TITLE);
                        String courseLessonContent = getStringValue(lessonsJSON, COURSE_LESSON_CONTENT);

                        Lesson lesson = new Lesson(courseLessonTitle, courseLessonContent, courseLessonNumber);   
                        lessons.add(lesson);
                    }

                    JSONObject quizJSON = (JSONObject) modulesJSON.get(COURSE_QUIZZES);
                    
                    //choices for user. 
                    JSONArray questionJSON = (JSONArray) quizJSON.get(COURSE_QUESTIONS);

                    ArrayList<Question> questions = new ArrayList<>();
                    for (int l = 0; l < questionJSON.size(); l++) {
                        JSONObject questionsJSON = (JSONObject) questionJSON.get(l);
                        
                        String courseQuestion = getStringValue(questionsJSON, COURSE_QUESTION_PROMPT);

                        ArrayList<String> choices = new ArrayList<>();
                        String courseAnswerA = getStringValue(questionsJSON, COURSE_ANSWER_A);
                        String courseAnswerB = getStringValue(questionsJSON, COURSE_ANSWER_B);
                        String courseAnswerC = getStringValue(questionsJSON, COURSE_ANSWER_C);
                        String courseAnswerD = getStringValue(questionsJSON, COURSE_ANSWER_D);
                        
                        // Get the correct answer as an integer
                        int courseCorrectAnswer = getIntValue(questionsJSON, COURSE_CORRECT_ANSWER, 1);

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
                    UUID userID = UUID.fromString(getStringValue(resultsJSON, USER_ID));

                    //parse through grade
                    ArrayList<Double> grades = new ArrayList<>();
                    Object gradesObj = resultsJSON.get(COURSE_GRADE);
                    
                    // Handle different possible JSON structures for grades
                    if (gradesObj instanceof ArrayList) {
                        // Direct ArrayList
                        grades = (ArrayList<Double>) gradesObj;
                    } else if (gradesObj instanceof JSONArray) {
                        // JSON Array
                        JSONArray gradesArray = (JSONArray) gradesObj;
                        for (int k = 0; k < gradesArray.size(); k++) {
                            Object gradeObj = gradesArray.get(k);
                            if (gradeObj instanceof Double) {
                                grades.add((Double) gradeObj);
                            } else if (gradeObj instanceof Long) {
                                grades.add(((Long) gradeObj).doubleValue());
                            } else if (gradeObj instanceof JSONObject) {
                                Object grade = ((JSONObject) gradeObj).get(COURSE_GRADE);
                                if (grade instanceof Double) {
                                    grades.add((Double) grade);
                                } else if (grade instanceof Long) {
                                    grades.add(((Long) grade).doubleValue());
                                } else if (grade instanceof String) {
                                    try {
                                        grades.add(Double.parseDouble((String) grade));
                                    } catch (NumberFormatException e) {
                                        grades.add(0.0);
                                    }
                                }
                            } else if (gradeObj instanceof String) {
                                try {
                                    grades.add(Double.parseDouble((String) gradeObj));
                                } catch (NumberFormatException e) {
                                    grades.add(0.0);
                                }
                            }
                        }
                    } else if (gradesObj instanceof JSONObject) {
                        // Single grade as JSONObject
                        Object grade = ((JSONObject) gradesObj).get(COURSE_GRADE);
                        if (grade instanceof Double) {
                            grades.add((Double) grade);
                        } else if (grade instanceof Long) {
                            grades.add(((Long) grade).doubleValue());
                        } else if (grade instanceof String) {
                            try {
                                grades.add(Double.parseDouble((String) grade));
                            } catch (NumberFormatException e) {
                                grades.add(0.0);
                            }
                        }
                    }

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
    }  
}
