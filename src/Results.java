package src;

import java.util.UUID;
import java.util.ArrayList;

public class Results {
    private UUID userID;
    private ArrayList<Double> grades = new ArrayList<Double>();

    public Results() {
        
    }

    /**
    * Method to set the id and grades to allow a grade to be connected to a specific user.
    * @param userID unique ID for users that can determine who they are.
    * @param grades a double list of the grades a user has aquired from the coursework they have done.
    */
    public Results(UUID userID, ArrayList<Double> grades) {
        setUUID(userID);
        setGrades(grades);
    }

    /**
    * Method accessing userID so it can be called.
    * @return a unique ID for a user is accessed.
    */
    public UUID getUUID() {
        return this.userID;
    }

    /**
    * Method to mutate an accessed userID.
    * @param userID unique ID for users that can determine who they are.
    */
    public void setUUID(UUID userID) {
        this.userID = userID;
    }

    /**
    * Method that can access the list of grades a user has gotten for assignments.
    * @return list of the different grades aquired by a user
    */
    public ArrayList<Double> getGrades() {
        return this.grades;
    }

    /**
    * Method to mutate the grades, changing the list if there were any changes.
    * @param grades an array list that has each double of grade that is stored for each user.
    */
    public void setGrades(ArrayList<Double> grades) {
        this.grades = grades;
    }


    
    public String toString() {
        return "\nUser Grades: " + this.getGrades();
    }
}
