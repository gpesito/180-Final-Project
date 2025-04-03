import java.util.ArrayList;

public interface IMessageService {
    void sendMessage(int senderId, int receiverId, String messageContent);
    void deleteMessage(int messageId);
    ArrayList<Message> getAllMessages(int userId);
}
