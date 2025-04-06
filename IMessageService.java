import java.util.ArrayList;
/**
 * Interface that includes the actions that can be done with the use of Message objects, primarily sending/deleting.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 6, 2025
 */
public interface IMessageService {
    void sendMessage(int senderId, int receiverId, String messageContent);
    void deleteMessage(int messageId);
    ArrayList<Message> getAllMessages(int userId);
}
