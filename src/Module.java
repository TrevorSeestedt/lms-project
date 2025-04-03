package src;

import java.util.ArrayList;

public class Module {
    private String prompt;
    private String title;
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private Quiz quiz;
    private double grade = 0.0;

    /**
    * Sets the prompt and quiz. calls addLessons using array lessons.
    */
    public Module() {
        setPrompt(prompt);
        addLessons(lessons);
        setQuiz(quiz);
    }

    /**
    * Sets the prompt and quiz. calls addLessons using array lessons.
    * @param prompt The questions the user will be asked in this module.
    * @param lessons The lessons that are within this module.
    * @param quiz The quiz that is in this module.
    */
    public Module(String prompt, ArrayList<Lesson> lessons, Quiz quiz) {
        setPrompt(prompt);
        setTitle(prompt); // Using prompt as title for backward compatibility
        addLessons(lessons);
        setQuiz(quiz);
    }

    /**
    * Gets the title for this module.
    * @return accesses the string title for this module.
    */
    public String getTitle() {
        return this.title != null ? this.title : this.prompt; // Fall back to prompt if title is null
    }

    /**
    * Sets the title for this module.
    * @param title changes the title for this module if it has been altered.
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
    * Gets the prompt for this module.
    * @return accesess the string prompt for this module.
    */
    public String getPrompt() {
        return this.prompt;
    } 

    /**
    * Sets the prompt for this module.
    * @param prompt changes the prompt for this module if it has been altered.
    */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
    * Gets the lessons for this module.
    * @return
    */
    public ArrayList<Lesson> getLessons() {
        return this.lessons;
    }

    /**
    * adds a lesson for this module.
    * @param lesson
    */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * removes a lesson from the current module's list of lessons
     * @param lesson
     */
    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    /**
    * adds an array list lessons to this module.
    * @param lessons 
    */
    public void addLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Lesson getLessonByLessonTitle(String lessonTitle) {
        for(Lesson lesson : lessons) {
            if(lesson.getTitle().equals(lessonTitle)) {
                return lesson;
            }
        }
        return null;
    }

    /**
    * prints the title of the lesson and the lesson's contents in this module.
    * @param lesson array list of the lessons in this module.
    */
    public void printLesson(Lesson lesson) {
        System.out.println(lesson.getTitle());
        System.out.println(lesson.getContents());
    }

    /**
    * Gets the quiz for this module.
    * @return Accesses the quiz for this module.
    */
    public Quiz getQuiz() {
        return this.quiz;
    }

    /**
    * Sets the quiz for this module.
    * @param quiz Parameters needed to load a quiz in the program.
    */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public double getGrade() {
        return this.grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String toString() {
        return "Module: " + this.getTitle() + "\nModule Grade: " + this.getGrade();
    }
    
}
