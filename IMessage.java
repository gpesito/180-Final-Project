import java.time.LocalDateTime;
/**
 * Interface that defines the blueprint for a Message object and methods that are used to acces important fields.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 6, 2025
 */
public interface IMessage {
    String getMessageId();
    String getSenderId();
    String getReceiverId();
    String getProductId();
    String getMessageContent();
    void setMessageContent(String messageContent);
    String toString();
    LocalDateTime getTimestamp();
}
