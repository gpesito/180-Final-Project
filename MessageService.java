import java.util.ArrayList;

public class MessageService implements IMessageService {
    private ArrayList<Message> messages;
    private int messageCount;

    public MessageService() {
        this.messages = new ArrayList<Message>();
    }

    @Override
    public void sendMessage(int senderId, int receiverId, String messageContent) {
        Message message = new Message(messageCount++, senderId, receiverId, messageContent);
        messages.add(message);
    }

    @Override
    public void deleteMessage(int messageId) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getMessageId() == messageId) {
                messages.remove(i);
                return;
            }
        }
    }

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
