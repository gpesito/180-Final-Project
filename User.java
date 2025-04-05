import java.util.HashSet;
import java.util.Set;

/**
 * Represents a single User in the system, handling user-specific data
 * such as userID, username, email, password, and user actions like
 * login, logout, adding friends, blocking users
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Alice Nguyen
 * @version April 1, 2025
 */

public class User implements UserInterface {
    private String userId;
    private String username;
    private String email;
    private String password;
    private double balance;
    private Set<UserInterface> friends;
    private Set<UserInterface> blockedUsers;

    public User(String userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = 0.0;
        this.friends = new HashSet<>();
        this.blockedUsers = new HashSet<>();
    }
    
    @Override
    public String getUserId() {
        return userId;
    }
    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public double getBalance() {
        return balance;
    }
    @Override
    public void updateBalance(double amount) {
        this.balance = amount;
    }
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    @Override
    public void logout() {
        // Add logout logic if needed
    }
    @Override
    public void addFriend(UserInterface friend) {
        friends.add(friend);
    }
    @Override
    public void blockUser(UserInterface user) {
        blockedUsers.add(user);
    }
}
