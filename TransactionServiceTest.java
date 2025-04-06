import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionServiceTest {

    private User buyer;
    private User seller;
    private TransactionService transactionService;

    public void setUp() {
        buyer = new User("1", "Buyer", "buyer@example.com", "password123");
        seller = new User("2", "Seller", "seller@example.com", "password123");
        transactionService = new TransactionService(buyer, seller, 200);
    }

    public void testCreateTransaction_Successful() {
        Transaction transaction = new Transaction(buyer, seller, 200);
        String result = transactionService.createTransaction(transaction);
        assertEquals("Transaction successful: ", result);
        assertEquals(800, buyer.getBalance(), 0.01);
        assertEquals(700, seller.getBalance(), 0.01);
    }

    public void testCreateTransaction_InsufficientFunds() {
        Transaction transaction = new Transaction(buyer, seller, 1500);
        String result = transactionService.createTransaction(transaction);
        assertEquals("Transaction validation failed.", result);
        assertEquals(1000, buyer.getBalance(), 0.01);
        assertEquals(500, seller.getBalance(), 0.01);
    }

    public void testCreateTransaction_SenderAndRecipientAreSame() {
        Transaction transaction = new Transaction(buyer, buyer, 200);
        String result = transactionService.createTransaction(transaction);
        assertEquals("Transaction validation failed.", result);
        assertEquals(1000, buyer.getBalance(), 0.01);
        assertEquals(500, seller.getBalance(), 0.01);
    }
}
