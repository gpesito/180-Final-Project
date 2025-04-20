public interface TransactionServiceInterface {
    /**
     * Declares methods and parameters for Transaction Service class
     *
     * <p>Purdue University -- CS18000 -- Spring 2025</p>
     *
     * @author Gabrielle Pesito
     * @version April 20, 2025
     */
    String createTransaction(Transaction transaction);
    boolean validateTransaction(User sender, User recipient, double amount);
    boolean processTransaction(User sender, User recipient, double amount);
}
