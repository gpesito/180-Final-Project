import java.util.ArrayList;
/**
 * Class that allows user to send, delete, or view alll messages by a specific user.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 6, 2025
 */
public class MessageService implements IMessageService {
    private ArrayList<Message> messages;
    private int messageCount;

    public MessageService() {
        this.messages = new ArrayList<Message>();
    }
    // Sends new message and adds message to list that contains all messages.
    @Override
    public void sendMessage(int senderId, int receiverId, String messageContent) {
        Message message = new Message(messageCount++, senderId, receiverId, messageContent);
        messages.add(message);
    }
    // Deletes message from "storage" in our list.
    @Override
    public void deleteMessage(int messageId) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getMessageId() == messageId) {
                messages.remove(i);
                return;
            }
        }
    }
    // Uses a specific user's ID to access messages they have sent. 
    public ArrayList<Message> getAllMessages(int userId) {
        ArrayList<Message> userMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSenderId() == userId || message.getReceiverId() == userId) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }
}
