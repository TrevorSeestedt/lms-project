package Tank;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.UUID;

public class UserListTest {
    
    @Test
    public void testAddUser() {
        UserList userList = UserList.getInstance();
        ArrayList<RegisteredUser> users = userList.getUsers();
        UUID userID = UUID.randomUUID();
        String type = "student";
        String firstName = "Aidan";
        String lastName = "Macklen";
        String username = "amacklen";
        String password = "password123";
        String email = "amacklen@gmail.com";
        String DOB = "01/24/2003";
        ArrayList<Course> currentCourses = new ArrayList<Course>();
        UserList.addUser(userID, type, firstName, lastName, username, password, email, DOB, currentCourses);
        RegisteredUser user = userList.getUserByUsername(username);
        assertNotNull(user);
        assertEquals(userID, user.getUUID());
        assertEquals(type, user.getType());
        assertEquals(firstName, user.getFirstName());
        assertEquals(username, user.getUserName());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getUserEmail());
        assertEquals(DOB, user.getUserDOB());
        assertEquals(currentCourses, ((Student) user).getCurrentCourses());
    }

    @Test
    public void testGetUser() {
        UserList userList = UserList.getInstance();
        RegisteredUser user = userList.getUserByUsername("amacklen");
        assertNotNull(user);
        assertEquals("Aidan", user.getFirstName());
        assertEquals("Macklen", user.getLastName());
    }

    @Test
    public void testGetUsers() {
        UserList userList = UserList.getInstance();
        ArrayList<RegisteredUser> users = userList.getUsers();
        assertNotNull(users);
    }

    @Test
    public void testGetUserByUUID() {
        UserList userList = UserList.getInstance();
        RegisteredUser user = userList.getUserByUsername("amacklen");
        UUID userID = user.getUUID();
        RegisteredUser sameUser = userList.getUserByUUID(userID);
        assertNotNull(sameUser);
        assertEquals("Aidan", sameUser.getFirstName());
        assertEquals("Macklen", sameUser.getLastName());
    }

    @Test
    public void testGetUserByUsername() {
        UserList userList = UserList.getInstance();
        RegisteredUser user = userList.getUserByUsername("amacklen");
        assertNotNull(user);
        assertEquals("Aidan", user.getFirstName());
        assertEquals("Macklen", user.getLastName());
    }
}
