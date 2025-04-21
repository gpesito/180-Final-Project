/**
 * Interface for user service operations such as registration, deletion,
 * retrieval, and authentication.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Alice Nguyen
 * @version April 1, 2025
 */

public interface UserServiceInterface {
    boolean registerUser(UserInterface user);
    boolean loginUser(String username, String passowrd);
    boolean deleteUser(String userId);
    UserInterface getUserById(String userId);
}
