import server.MarketplaceServer;
import services.*;

public class Client {
    public static void main(String[] args) {
        MarketplaceServer server = new MarketplaceServer(
                new UserService(),
                new ProductService(),
                new TransactionService(),
                new MessageService()
        );

    }
}
