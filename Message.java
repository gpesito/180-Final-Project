/**
 * Class that defines features and methods of a Message object that can be sent by users to one another.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 6, 2025
 */
public class Message implements IMessage {
    // Uses ID values to track messages, as well as users that will see them and what content is in the messages.
    private int messageId;
    private int senderId;
    private int receiverId;
    private String messageContent;

    public Message(int messageId, int senderId, int receiverId, String messageContent) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = messageContent;
    }
    // Provides get methods to return ID values.
    @Override
    public int getMessageId() {
        return messageId;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }

    @Override
    public int getReceiverId() {
        return receiverId;
    }

    @Override
    public String getMessageContent() {
        return messageContent;
    }
    // Set method for message content, only mutable field necessary.
    @Override
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
