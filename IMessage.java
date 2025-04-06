/**
 * Interface that defines the blueprint for a Message object and methods that are used to acces important fields.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 6, 2025
 */
public interface IMessage {
    int getMessageId();
    int getSenderId();
    int getReceiverId();
    String getMessageContent();
    void setMessageContent(String messageContent);
}
