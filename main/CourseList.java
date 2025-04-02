package Tank;

import java.util.ArrayList;
import java.util.UUID;

public class CourseList {
    private static ArrayList<Course> courses = new ArrayList<Course>();
    private static CourseList courseList = new CourseList();

    /**
    * Sets getCourses in DataLoader equal to a variable courses.
    */
    private CourseList() {
        courses = DataLoader.getCourses();
    }

    /**
    * makes a new course list if this course list is a null value.
    * @return All of the courses in this course list.
    */
    public static CourseList getInstance() {
        if(courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    /**
    * Allows the ability to add a new course in this course list.
    * @param courseID UUID for the course in this course list.
    * @param title name of the course in this course lsit.
    * @param dateCreated date the course was added to this course list.
    * @param language language the course is taught in.
    * @param author the user that created the course
    * @param courseDifficulty how rigerous the course is.
    * @param review judging a courses quality in this course list.
    * @param courseSummary summarization of the course in this course list.
    * @param modules The modules in the course to be added to this course list.
    * @param results The UUID and list of grades a student has for this course list.
    */
    public void addCourse(UUID courseID, String title , String dateCreated, String language, Author author, String courseDifficulty, ArrayList<Review> review, String courseSummary, ArrayList<Module> modules, ArrayList<Results> results) {
        courses.add(new Course(courseID, title, dateCreated, language, author, courseDifficulty, review, courseSummary, modules, results));
        DataWriter.saveCourses();
    }

    public void addCourse(Course course) {
        courses.add(course);
        //DataWriter.saveCourses();
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    /**
    * Uses a for each loop to go through the elements of the array courses until the program finds courses with matching ID's
    * @param courseID UUID for this course.
    * @return the variables required for a course to function.
    */
    public Course getCourse(UUID courseID) {
        for(Course course : courses) {
            if(course.getCourseID().equals(courseID)) {
                return course;
            }
        }
        return null;
    }

    /*public Course getCourseByCourseTitle(String courseTitle) {
        for(Course course : courses) {
            if(course.getTitle().equals(courseTitle)) {
                return course;
            }
        }
        return null;
    }*/

    public Course getCourseByCourseTitle(String courseTitle) {
        for(int i=0;i<courses.size();++i) {
            if(courses.get(i).getTitle().equalsIgnoreCase(courseTitle)) {
                return courses.get(i);
            }
        }
        return null;
    }

    /**
    * Gets courses for this course list.
    * @return The list of courses in this courses list. 
    */
    public static ArrayList<Course> getCourses() {
        return courses;
    }
}
