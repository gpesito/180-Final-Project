public class Transaction implements TransactionInterface {
    private User buyer;
    private User seller;
    private double amount;
    /**
     * Gabby Pesito
     */

    //Constructor
    public Transaction(User buyer, User seller, double amount) {
        this.buyer = buyer;
        this.seller = seller;
        this.amount = amount;
    }

    // Getter and Setter methods
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
    // Method to process the payment
    public void processPayment() {
        if (buyer.getBalance() >= amount) {
            // Deduct the amount from the buyer's balance and add it to the seller's balance
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