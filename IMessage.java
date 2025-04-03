public interface IMessage {
    int getMessageId();
    int getSenderId();
    int getReceiverId();
    String getMessageContent();
    void setMessageContent(String messageContent);
}
