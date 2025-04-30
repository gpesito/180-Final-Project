import java.io.IOException;
/**
 * IClient interface defines the contract for client-side operations
 * in a client-server communication model.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * Jay Saini @author
 * @version April 30, 2025
 */

public interface IClient {


    void connect(String serverAddress, int serverPort) throws IOException;


    void sendCommand(String command) throws IOException;


    String readResponse() throws IOException;


    void disconnect() throws IOException;


    void launchGUI();
}
