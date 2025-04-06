import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Test cases for the Message class that tests its methods and ensures the correct values for each field are returned.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 6, 2025
 */
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
