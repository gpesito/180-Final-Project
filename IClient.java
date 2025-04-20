/**
 * IClient interface defines the contract for client-side operations
 * in a client-server communication model.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * Jay Saini @author
 * @version April 20, 2025
 */
public interface IClient {


    void connect(String serverAddress, int serverPort) throws Exception;


    void sendCommand(String command) throws Exception;

    String readResponse() throws Exception;


    void disconnect() throws Exception;
}
