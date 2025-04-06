import server.MarketplaceServer;
import services.*; //imports our service classes
/**
 * This is the Client class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025 -- Team Project
 *
 * @author Jay Saini Purdue CS
 * @version April 6, 2025
 */

public class Client implements IClient {
    private final MarketplaceServer server;
//the constructor that accepts MarketplaceServer instances
    public Client(MarketplaceServer server) {
        this.server = server;
    }

    @Override
    public void start() {
        
        System.out.println("Starting");
    }

    public static void main(String[] args) {
        MarketplaceServer server = new MarketplaceServer(
            new UserService(),
            new ProductService(),
            new TransactionService(),
            new MessageService()
        );
        //launches the client
        new Client(server).start();
    }
}
