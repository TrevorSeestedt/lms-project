package src;

public class Grades {
    private Student student;
    private double grade;
    private Course course;

    /**
    * Sets The course, grade, and student.
    * @param course The values required for a course to function.
    * @param grade The double value of the score for this grade.
    * @param student User that will be graded on assignments.
    */
    public Grades(Course course, double grade, Student student) {
        setCourse(course);
        setGrade(grade);
        setStudent(student);
    }

    /**
    * Sets the calculated grade. 
    * @param grade double value of a student's grade.
    */
    public void CalculateGrade(double grade) {
        setGrade(0.0);
    }

    /**
    * Gets the double value for this grade.
    * @return acesses the grade value for this grade.
    */
    public double getGrade() {
        return this.grade;
    }

    /**
    * Sets the double value for this grade.
    * @param grade double value that represents a user's grade.
    */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
    * Gets the student for this grade.
    * @return accesses the student for this grade.
    */
    public Student getStudent() {
        return this.student;
    }

    /**
    * Sets the student for this grade.
    * @param student User that will be graded on assignments.
    */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
    * Gets the course for this grade.
    * @return acesses the course for this grade.
    */
    public Course getCourse() {
        return this.course;
    }

    /**
    * Sets the course for this grade.
    * @param course holds the values that a course requires to function.
    */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
    * Creates a string representing the course and grade the studenet got for the course.
    * @return A string of the course and the score for this grade.
    */
    public String toString() {
        return "Course: " + this.getCourse() + "\nGrade: " + this.getGrade();
    }
}
