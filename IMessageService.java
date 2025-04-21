import java.util.ArrayList;
/**
 * Interface that includes the actions that can be done with the use of Message objects, primarily sending/deleting.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 18, 2025
 */
public interface IMessageService {
    void sendMessage(String senderId, String receiverId, String messageContent, String productId);
    void deleteMessage(String messageId);
    ArrayList<Message> getAllMessages(String userId);
}
