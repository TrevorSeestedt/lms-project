package src;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static ArrayList<RegisteredUser> users;
    private static UserList userList = null;
    
    /**
    * stores the information of the Users in DataLoader as a variable user.
    */
    private UserList() {
        users = DataLoader.getUsers();
    }
    
    /**
    * @return null value is returned
    */
    public static UserList getInstance() {
        if(userList == null) {
            userList = new UserList();
        }

        return userList;
    }
    /**
    * Adds needed information for new users into the system as either a student or author
    * @param userID the UUID unique to each user.
    * @param type String used to label a user as a student or author.
    * @param firstName String for the first name of the user.
    * @param lastName String for the last name of the user.
    * @param username String that is the name given by user on login.
    * @param password String that is input by the user to confirm the user has input the correct username.
    * @param email String of the user's electronic mailing address.
    * @param DOB String that will have the user's birth date.
    * @param currentCourses Array list of the courses a student is currently a part of.
    * @param grades a double value of the grades the student user has aquired from the course assignment.
    */
    public static void addUser(UUID userID, String type, String firstName, String lastName, String username, String password, String email, String DOB, ArrayList<Course> currentCourses) {
        if(type.equalsIgnoreCase("student"))
            users.add(new Student(userID, type, firstName, lastName, username, password, email, DOB, currentCourses));
        else
            users.add(new Author(userID, type, firstName, lastName, username, password, email, DOB));
    }

    /**
    * for each loop that checks the user with the current saved list of users.
    * @param username The string for the login name of the users
    * @return user the values of a person's registration information, email, and grades.
    */
    public RegisteredUser getUser(String username) {
        for(RegisteredUser user : users) {
            if(user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
    * @return the array of all users in the system
    */
    public ArrayList<RegisteredUser> getUsers() {
        return users;
    }

    /**

    */
    public void editUser() {

    }

    /**
    * stores the users into the system using DataWriter.
    */
    public void saveUsers() {
        DataWriter.saveUsers();
    }

    /**
    * stores the users information using DataWriter on logout.
    */
    public void logout() {
        DataWriter.saveUsers();
    }

    /**
    * checks a user's ID against the list of UUID's to determine if there is a match.
    * @param userID the UUID that is unique to each user.
    * @return user the values of a person's registration information, email, and grades.
    */
    public RegisteredUser getUserByUUID(UUID userID) {
        RegisteredUser user = null;
        for(RegisteredUser u : users) {
            if (u.getUUID().equals(userID)) {
                user = u;
            }
        }
        return user;
    }

    public RegisteredUser getUserByUsername(String username){
        for (int i=0; i<users.size(); i++){
            if(users.get(i).getUserName().equalsIgnoreCase(username)){
                return users.get(i);
            }
        }
        return null;
    }
}
