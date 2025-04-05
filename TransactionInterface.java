public interface TransactionInterface {

    User getBuyer();
    void setBuyer(User buyer);
    User getSeller();
    void setSeller(User seller);
    double getAmount();
    void setAmount(double amount);
    
    void processPayment();
}
