public class TransactionService extends Transaction implements TransactionServiceInterface{
    /**
     * Gabby Pesito
     */
    private User buyer;
    private User seller;
    private double amount;

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

    //Checks if the sender/recipient are valid, and if balance is sufficent
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
}
