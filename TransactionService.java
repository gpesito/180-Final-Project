import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/**
 * Creates and handles transaction objects, while checking that the transaction involves
 * two distinct user that have adequate balances
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Gabrielle Pesito
 * @version April 20, 2025
 */
public class TransactionService extends Transaction implements TransactionServiceInterface {

    private User buyer;
    private User seller;
    private double amount;
    
    // === Phase 1 (unchanged) ===

    // Constructors
    public TransactionService(User buyer, User seller, double amount) {
        super(buyer, seller, amount);
    }

    // Getters and Setters
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //creates a transaction object as a string and validates inputs
    public String createTransaction(Transaction transaction) {
        User sender = transaction.getBuyer();
        User recipient = transaction.getSeller();
        double amount = transaction.getAmount();

        if (!validateTransaction(sender, recipient, amount)) {
            return "Transaction validation failed.";
        }

        if (processTransaction(sender, recipient, amount)) {
            return "Transaction successful: ";
        } else {
            return "Transaction processing failed.";
        }
    }

    //Checks if the sender/recipient are valid, and if balance is sufficient
    public boolean validateTransaction(User sender, User recipient, double amount) {
        if (sender == recipient) {
            System.out.println("Sender and recipient cannot be the same.");
            return false;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Sender has insufficient funds. Current balance: " + sender.getBalance());
            return false;
        }

        if (recipient == null) {
            System.out.println("Recipient account does not exist.");
            return false;
        }

        return true;
    }

    //uses payment method from transaction class to process a payment while returning a boolean to validate
    public boolean processTransaction(User sender, User recipient, double amount) {
        processPayment(sender, recipient, amount);
        return true;
    }


    // === Phase 2  ===

    // Thread-safe storage of all transaction records
    private static final List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());

     //Creates a transaction using direct parameters

    public String createTransaction(User buyer, User seller, double amount) {
        if (!validateTransaction(buyer, seller, amount)) {
            return "Transaction validation failed.";
        }

        Transaction newTransaction = new Transaction(buyer, seller, amount);
        processTransaction(buyer, seller, amount);
        transactions.add(newTransaction);
        return "Transaction successful and recorded.";
    }


    public List<Transaction> getTransactionsForUser(String userId) {
        List<Transaction> userTransactions = new ArrayList<>();

        synchronized (transactions) {
            for (Transaction t : transactions) {
                if (t.getBuyer().getUserId().equals(userId) || t.getSeller().getUserId().equals(userId)) {
                    userTransactions.add(t);
                }
            }
        }

        return userTransactions;
    }
}
