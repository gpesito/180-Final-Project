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
    public void setUp() {
        buyer = new User("1", "Buyer", "buyer@example.com", "password");
        seller = new User("2", "Seller", "seller@example.com", "password");
    }

    //tests process payment
    public void testProcessPaymentSuccess() {
        double initialBuyerBalance = buyer.getBalance();
        double initialSellerBalance = seller.getBalance();
        double transactionAmount = 30.0;

        buyer.updateBalance(100.0);
        seller.updateBalance(50.0);

        transaction = new Transaction(buyer, seller, transactionAmount);
        transaction.processPayment();

        assertEquals(initialBuyerBalance - transactionAmount, buyer.getBalance(), 0.01);
        assertEquals(initialSellerBalance + transactionAmount, seller.getBalance(), 0.01);
    }

    //checks process payment in cases of insufficent funds
    public void testProcessPaymentFailureInsufficientFunds() {
        double initialBuyerBalance = buyer.getBalance();
        double initialSellerBalance = seller.getBalance();
        double transactionAmount = 150.0;

        buyer.updateBalance(100.0);
        seller.updateBalance(50.0);

        transaction = new Transaction(buyer, seller, transactionAmount);
        transaction.processPayment();

        assertEquals(initialBuyerBalance, buyer.getBalance(), 0.01);
        assertEquals(initialSellerBalance, seller.getBalance(), 0.01);
    }

    //test getters and setters return the correct value
    public void testGetBuyer() {
        transaction = new Transaction(buyer, seller, 50.0);
        assertEquals(buyer, transaction.getBuyer());
    }

    public void testSetBuyer() {
        transaction = new Transaction(buyer, seller, 50.0);
        User newBuyer = new User("3", "NewBuyer", "newbuyer@example.com", "password");
        transaction.setBuyer(newBuyer);
        assertEquals(newBuyer, transaction.getBuyer());
    }

    public void testGetSeller() {
        transaction = new Transaction(buyer, seller, 50.0);
        assertEquals(seller, transaction.getSeller());
    }

    public void testSetSeller() {
        transaction = new Transaction(buyer, seller, 50.0);
        User newSeller = new User("4", "NewSeller", "newseller@example.com", "password");
        transaction.setSeller(newSeller);
        assertEquals(newSeller, transaction.getSeller());
    }

    public void testGetAmount() {
        double amount = 100.0;
        transaction = new Transaction(buyer, seller, amount);
        assertEquals(amount, transaction.getAmount(), 0.01);
    }

    public void testSetAmount() {
        transaction = new Transaction(buyer, seller, 50.0);
        transaction.setAmount(75.0);
        assertEquals(75.0, transaction.getAmount(), 0.01);
    }
}