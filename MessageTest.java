import static org.junit.Assert.*;
import org.junit.Test;

public class MessageTest {
    @ Test
    public void testNewMessage() {
        Message message = new Message(1, 100, 200, "Test Message");

        assertEquals("Message ID is incorrect!", 1, message.getMessageId());
        assertEquals("Sender ID is incorrect!", 100, message.getSenderId());
        assertEquals("Receiver ID is incorrect!", 200, message.getReceiverId());
        assertEquals("Message content is incorrect!", "Test Message", message.getMessageContent());

    }

    @ Test
    public void testSetContent() {
        Message message = new Message(2, 101, 201, "Initial Message");
        message.setMessageContent("New Message");

        assertEquals("Content wasn't updated correctly ", "New Message", message.getMessageContent());
    }
}
