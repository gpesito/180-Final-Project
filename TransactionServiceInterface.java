public interface TransactionServiceInterface {
    /**
     * Gabby Pesito
     */
    String createTransaction(Transaction transaction);
    boolean validateTransaction(User sender, User recipient, double amount);
    boolean processTransaction(User sender, User recipient, double amount);
}
