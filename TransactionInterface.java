public interface TransactionInterface {
    /**
     * Decloares methods and parameters for Transaction class
     *
     * <p>Purdue University -- CS18000 -- Spring 2025</p>
     *
     * @author Gabrielle Pesito
     * @version April 20, 2025
     */
    User getBuyer();
    void setBuyer(User buyer);
    User getSeller();
    void setSeller(User seller);
    double getAmount();
    void setAmount(double amount);
    
    void processPayment(User buyer, User seller, double amount);
}
