import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;
/**
 * Class that allows user to send, delete, or view alll messages by a specific user.
 * 
 * Purdue University -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 28, 2025
 */
public class MessageService implements IMessageService {
    private final List<Object> messages;
    private int messageCount;
    

    public MessageService() {
        this.messages = Collections.synchronizedList(new ArrayList<>());
        this.messageCount = 0;
    }
    
    // Sends new message and adds message to list that contains all messages.
    @Override
    public void sendMessage(String senderId, String receiverId, String messageContent, String productId) {
        String id = String.valueOf(messageCount++);
        Message message = new Message(id, senderId, receiverId, productId, messageContent, LocalDateTime.now());
        messages.add(message);
    }
    // Deletes message from "storage" in our list.
    @Override
    public void deleteMessage(String messageId) {
        for (int i = 0; i < messages.size(); i++) {
            if (((Message) messages.get(i)).getMessageId().equals(messageId)) {
                messages.remove(i);
                return;
            }
        }
    }
    // Uses a specific user's ID to access messages they have sent. 
    public ArrayList<Message> getAllMessages(String userId) {
        ArrayList<Message> userMessages = new ArrayList<>();
        synchronized (messages) {
            for (Object message : messages) {
                if (((Message) message).getSenderId().equals(userId) || ((Message) message).getReceiverId().equals(userId)) {
                    userMessages.add((Message) message);
                }
            }
        }
        return userMessages;
    }
}
