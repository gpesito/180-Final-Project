import org.junit.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Test cases for the User and UserService classes.
 *
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Alice Nguyen
 * @version April 3, 2025
 */

public class UserTestCases {
    @Test
    public void testUserInitialization() {
        System.out.println("Testing User initialization...");
        User user = new User("U1", "Alice", "alice@example.com", "password123");
        assertEquals("U1", user.getUserId());
        assertEquals("Alice", user.getUsername());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(0.0, user.getBalance(), 0.001);
    }

    @Test
    public void testUserLogin() {
        System.out.println("Testing login functionality...");
        User user = new User("U1", "Alice", "alice@example.com", "password123");
        assertTrue(user.login("Alice", "password123"), "Login should succeed with correct credentials");
        assertFalse(user.login("Alice", "wrongpassword"), "Login should fail with incorrect password");
    }
  
    @Test
    public void testUpdateBalance() {
        System.out.println("Testing balance update...");
        User user = new User("U1", "Alice", "alice@example.com", "password123");
        user.updateBalance(200.0);
        assertEquals(200.0, user.getBalance(), 0.001, "Balance should be updated to 200.0");
        user.updateBalance(-50.0);
        assertEquals(150.0, user.getBalance(), 0.001, "Balance should be updated to 150.0");
    }
  
    @Test
    public void testAddFriend() throws Exception {
        System.out.println("Testing addFriend functionality...");
        User user1 = new User("U1", "Alice", "alice@example.com", "password123");
        User user2 = new User("U2", "Bob", "bob@example.com", "bobpass");
        user1.addFriend(user2);
        
        Field friendsField = User.class.getDeclaredField("friends");
        friendsField.setAccessible(true);
        Set<?> friendsSet = (Set<?>) friendsField.get(user1);
        assertTrue(friendsSet.contains(user2), "User2 should be in user1's friend list");
        
        Field friendsField2 = User.class.getDeclaredField("friends");
        friendsField2.setAccessible(true);
        Set<?> friendsSet2 = (Set<?>) friendsField2.get(user2);
        assertFalse(friendsSet2.contains(user1), "User1 should not automatically be added to user2's friend list");
    }
    
    @Test
    public void testBlockUser() throws Exception {
        System.out.println("Testing blockUser functionality...");
        User user1 = new User("U1", "Alice", "alice@example.com", "password123");
        User user2 = new User("U2", "Bob", "bob@example.com", "bobpass");
        user1.blockUser(user2);
        
        Field blockedField = User.class.getDeclaredField("blockedUsers");
        blockedField.setAccessible(true);
        Set<?> blockedSet = (Set<?>) blockedField.get(user1);
        assertTrue(blockedSet.contains(user2), "User2 should be in user1's blocked list");
    }
  
    @Test
    public void testUserServiceRegister() {
        System.out.println("Testing user registration and retrieval...");
        UserService service = new UserService();
        User user = new User("U1", "Alice", "alice@example.com", "password123");
        assertTrue(service.registerUser(user), "User should be registered successfully");
    }
    
    @Test
    public void testUserServiceDelete() {
        System.out.println("Testing user deletion...");
        UserService service = new UserService();
        User user = new User("U1", "Alice", "alice@example.com", "password123");
        service.registerUser(user);
        
        assertTrue(service.deleteUser("U1"), "User deletion should return true");
        assertNull(service.getUserById("U1"), "User should no longer be retrievable after deletion");
    }
}


