public class Message implements IMessage {
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

    @Override
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
