import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;
/**
 * Test cases for the Message class that tests its methods and ensures the correct values for each field are returned.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 18, 2025
 */
public class MessageTest {
    @ Test
    public void testNewMessage() {
        LocalDateTime timestamp = LocalDateTime.now();
        Message message = new Message("1", "100", "200", "300", "Test Message", timestamp);

        assertEquals("Message ID is incorrect!", "1", message.getMessageId());
        assertEquals("Sender ID is incorrect!", "100", message.getSenderId());
        assertEquals("Receiver ID is incorrect!", "200", message.getReceiverId());
        assertEquals("Product ID is incorrect!", "300", message.getProductId());
        assertEquals("Message content is incorrect!", "Test Message", message.getMessageContent());
        assertEquals("Timestamp is incorrect!", timestamp, message.getTimestamp());

    }

    @ Test
    public void testSetContent() {
        LocalDateTime timestamp = LocalDateTime.now();
        Message message = new Message("2", "101", "201", "301", "Initial Message", timestamp);
        message.setMessageContent("New Message");
        assertEquals("Content wasn't updated correctly ", "New Message", message.getMessageContent());
    }
}
