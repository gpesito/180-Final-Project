public interface TransactionInterface {
    /**
     * Gabby
     */
    User getBuyer();
    void setBuyer(User buyer);
    User getSeller();
    void setSeller(User seller);
    double getAmount();
    void setAmount(double amount);
    
    void processPayment(User buyer, User seller, double amount);
}
