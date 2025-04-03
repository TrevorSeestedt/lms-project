package src;

public class Lesson {
    private String title;
    private String contents;
    private String lessonNumber;
    
    /**
    * Sets the title, contents, and lesson number for this lesson.
    * @param title The name that will be used for this lesson.
    * @param contents The written subject material for this lesson.
    * @param lessonNumber The string number that pertains to this lesson.
    */
    public Lesson(String title, String contents, String lessonNumber) {
        setTitle(title);
        setContents(contents);
        setLessonNumber(lessonNumber);

    }

    /**
    * A method that returns the parts that make up this lesson.
    * @return String that represents this lesson's number, title, and contents within.
    */
    public String toString() {
        return "Lesson Number: " + this.getLessonNumber() + "\nLesson Title: " + this.getTitle() + "\nLesson Contents: " + this.getContents();
    }

    /**
    * Gets the title for this lesson.
    * @return acesses the title for this lesson.
    */
    public String getTitle() {
        return this.title;
    }

    /**
    * Sets the title for this lesson.
    * @param title The name used for this lesson.
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
    * Gets the contents for this lesson.
    * @return acesses the contents for this lesson.
    */
    public String getContents() {
        return this.contents;
    }

    /**
    * Sets the contents of this lesson.
    * @param contents The written subject material of this lesson.
    */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
    * Gets the number that pertains to this lesson.
    * @return acesses this lesson's lesson number.
    */
    public String getLessonNumber() {
        return this.lessonNumber;
    }

    /**
    * Sets the number that pertains to this lesson.
    * @param lessonNumber The number of the lesson that's being taken.
    */
    public void setLessonNumber(String lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
