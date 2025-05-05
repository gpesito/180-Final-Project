import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
/**
 * Test cases for the MessageService class that initializes a new MessageService object and tests its methods.
 * 
 * Purdue Unversity -- CS180 -- Spring 2025 -- Team Project 
 * @author Savir Patil
 * @version April 18, 2025
 */
public class MessageServiceTest {
    private MessageService messageService;

    @Before
    public void setUp() {
        messageService = new MessageService();
    }

    /*** 
    @Test
    public void testSendMessage() {
        messageService.sendMessage("111", "222", "Hello!", "product1");

        ArrayList<Message> messages = messageService.getAllMessages("111");
        assertEquals("Message count is incorrect after sending!", 1, messages.size());
        assertEquals("Message content does not match expected!", "Hello!", messages.get(0).getMessageContent());
        assertEquals("Sender ID does not match expected!", "111", messages.get(0).getSenderId());
    }

    @Test
    public void testDeleteMessage() {
        messageService.sendMessage("111", "222", "Message to delete", "product2");
        ArrayList<Message> messagesBefore = messageService.getAllMessages("111");
        assertEquals("Message count should be 1 before deletion!", 1, messagesBefore.size());

        messageService.deleteMessage(messagesBefore.get(0).getMessageId());

        ArrayList<Message> messagesAfter = messageService.getAllMessages("111");
        assertEquals("Message was not deleted correctly!", 0, messagesAfter.size());
    }


    @Test
    public void testGetMessagesForUser() {
        messageService.sendMessage("101", "202", "First message", "product3");
        messageService.sendMessage("101", "202", "Second message", "product4");
        messageService.sendMessage("103", "202", "Another user’s message", "product5");

        String messages = messageService.getAllMessages("101");

        assertEquals("Incorrect number of messages retrieved for user!", 2, messages.size());
        assertEquals("First message content is incorrect!", "First message", messages.get(0).getMessageContent());
        assertEquals("Second message content is incorrect!", "Second message", messages.get(1).getMessageContent());
    }
     */

}
