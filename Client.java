import server.MarketplaceServer;
import services.*; //imports our service classes
//Client class - Jay

public class Client {
    public static void main(String[] args) {
        MarketplaceServer server = new MarketplaceServer(
                new UserService(),
                new ProductService(),
                new TransactionService(),
                new MessageService()
        );
//This helps the server to be ready for interactions
    }
}
