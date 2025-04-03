/**
 * Interface for user-related operations and data.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Alice Nguyen
 * @version April 1, 2025
 */

public interface UserInterface {
    String getUserId();
    void setUserId(String userId);
    
    String getUsername();
    void setUsername(String username);
    
    String getEmail();
    void setEmail(String email);
    
    String getPassword();
    void setPassword(String password);
    
    boolean login(String username, String password);
    void logout();
    
    void addFriend(UserInterface friend);
    void blockUser(UserInterface user);
    
    double getBalance();
    void updateBalance(double amount);
}
