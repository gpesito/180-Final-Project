import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * Tests expected outputs of each method in Transaction class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Gabrielle Pesito
 * @version April 20, 2025
 */
public class TransactionTest {

    private User buyer;
    private User seller;
    private Transaction transaction;

    //creates objects for testing
    @Before
    public void setUp() {
        buyer = new User("1", "Buyer", "buyer@example.com", "password");
        seller = new User("2", "Seller", "seller@example.com", "password");
    }

    //tests process payment
    @Test
    public void testProcessPaymentSuccess() {
        buyer.updateBalance(100.0);
        seller.updateBalance(50.0);

        double initialBuyerBalance  = buyer.getBalance();   // = 100.0
        double initialSellerBalance = seller.getBalance();  // = 50.0
        double transactionAmount    = 30.0;

        transaction = new Transaction(buyer, seller, transactionAmount);
        transaction.processPayment(buyer, seller, transactionAmount);

        assertEquals(initialBuyerBalance - transactionAmount, buyer.getBalance(), 0.01);
        assertEquals(initialSellerBalance + transactionAmount, seller.getBalance(), 0.01);
    }

    //checks process payment in cases of insufficent funds
    @Test
    public void testProcessPaymentFailureInsufficientFunds() {
        buyer.updateBalance(100.0);
        seller.updateBalance(50.0);

        double initialBuyerBalance = buyer.getBalance();
        double initialSellerBalance = seller.getBalance();
        double transactionAmount = 150.0;

        transaction = new Transaction(buyer, seller, transactionAmount);
        transaction.processPayment(buyer, seller, transactionAmount);

        assertEquals(initialBuyerBalance, buyer.getBalance(), 0.01);
        assertEquals(initialSellerBalance, seller.getBalance(), 0.01);
    }

    //test getters and setters return the correct value
    @Test
    public void testGetBuyer() {
        transaction = new Transaction(buyer, seller, 50.0);
        assertEquals(buyer, transaction.getBuyer());
    }
    @Test
    public void testSetBuyer() {
        transaction = new Transaction(buyer, seller, 50.0);
        User newBuyer = new User("3", "NewBuyer", "newbuyer@example.com", "password");
        transaction.setBuyer(newBuyer);
        assertEquals(newBuyer, transaction.getBuyer());
    }
    @Test
    public void testGetSeller() {
        transaction = new Transaction(buyer, seller, 50.0);
        assertEquals(seller, transaction.getSeller());
    }
    @Test
    public void testSetSeller() {
        transaction = new Transaction(buyer, seller, 50.0);
        User newSeller = new User("4", "NewSeller", "newseller@example.com", "password");
        transaction.setSeller(newSeller);
        assertEquals(newSeller, transaction.getSeller());
    }
    @Test
    public void testGetAmount() {
        double amount = 100.0;
        transaction = new Transaction(buyer, seller, amount);
        assertEquals(amount, transaction.getAmount(), 0.01);
    }
    @Test
    public void testSetAmount() {
        transaction = new Transaction(buyer, seller, 50.0);
        transaction.setAmount(75.0);
        assertEquals(75.0, transaction.getAmount(), 0.01);
    }
}