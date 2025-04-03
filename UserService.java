import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implements the user service operations.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Alice Nguyen
 * @version April 1, 2025
 */

public class UserService implements UserServiceInterface {
    // Thread-safe storage for users
    private Map<String, UserInterface> users = new ConcurrentHashMap<>();

    @Override
    public boolean registerUser(UserInterface user) {
        if (users.containsKey(user.getUserId())) {
            return false; // User already exists
        }
        users.put(user.getUserId(), user);
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        return users.remove(userId) != null;
    }

    @Override
    public UserInterface getUserById(String userId) {
        return users.get(userId);
    }
}

