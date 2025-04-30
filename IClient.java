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

    //connects server
    void connect(String serverAddress, int serverPort) throws IOException;

    //sends command  string to server
    void sendCommand(String command) throws IOException;

    //reads responses from server
    String readResponse() throws IOException;

    //disconnects from server
    void disconnect() throws IOException;


    void launchGUI();
}
