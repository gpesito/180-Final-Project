import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * Class that defines features and methods of a Message object that can be sent by users to one another.
 * 
 * Purdue University -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 28, 2025
 */
public class Message implements IMessage, Serializable {
    // Uses ID values to track messages, as well as users that will see them and what content is in the messages.
    private static final long serialVersionUID = 1L;
    private String messageId;
    private String senderId;
    private String receiverId;
    private String productId;
    private String messageContent;
    private LocalDateTime timestamp;

    public Message(String messageId, String senderId, String receiverId, String productId, String messageContent, LocalDateTime timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.productId = productId;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    // Constructor for message that does not use productId
    public Message(String messageId, String senderId, String receiverId, String content, LocalDateTime timestamp) {
        this(messageId, senderId, receiverId, null, content, timestamp);
    }
    // Provides get methods to return ID values.
    @Override
    public String getMessageId() {
        return messageId;
    }

    @Override
    public String getSenderId() {
        return senderId;
    }

    @Override
    public String getReceiverId() {
        return receiverId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String getMessageContent() {
        return messageContent;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    // Set method for message content, only mutable field necessary.
    @Override
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    // toString method to represent entire object.
    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", productId='" + productId + '\'' +
                ", content='" + messageContent + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
