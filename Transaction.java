import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
/**
 * Handles transactions between a buyer and seller user,
 * and appropriately updates values of each user object
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * @author Gabrielle Pesito
 * @version April 20, 2025
 */
public class Transaction implements TransactionInterface, Serializable {

    private static final long serialVersionUID = 1L;

    // Phase 1 Fields
    private User buyer;
    private User seller;
    private double amount;

    // Phase 2 fields
    private String transactionId;
    private String buyerId;
    private String sellerId;
    private String productId;
    private LocalDateTime timestamp;

    //contructor
    public Transaction(User buyer, User seller, double amount) {
        this.buyer = buyer;
        this.seller = seller;
        this.amount = amount;

        // Phase 2 new fields
        this.transactionId = UUID.randomUUID().toString();
        this.buyerId = buyer.getUserId(); 
        this.sellerId = seller.getUserId(); 
        this.productId = "";
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(User buyer, User seller, double amount, String productId) {
        this(buyer, seller, amount);
        this.productId = productId;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    // === Phase 1 (unchanged) ===

    @Override
    public User getBuyer() {
        return buyer;
    }

    @Override
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @Override
    public User getSeller() {
        return seller;
    }

    @Override
    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public void processPayment(User buyer, User seller, double amount) {
        if (buyer.getBalance() >= amount) {
            double buyerNewBalance = buyer.getBalance() - amount;
            double sellerNewBalance = seller.getBalance() + amount;

            buyer.updateBalance(buyerNewBalance);
            seller.updateBalance(sellerNewBalance);
            System.out.println("Transaction successful");
        } else {
            System.out.println("Transaction failed. Insufficient balance.");
        }
    }
}
