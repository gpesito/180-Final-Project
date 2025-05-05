
import java.io.*;
import java.net.Socket;
public class Client {
/**
 * GUI version of the client to communicate with the server
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * @author Milica Slavkovic, Gabby Pesito
 * @version May 5, 2025
 */
    private Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;

    // Constructor
    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);  // Connect to the server
            out = new PrintWriter(socket.getOutputStream(), true);  // Output stream to send data to the server
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // Input stream to read data from the server
            System.out.println("Connected to server at " + address + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to send a command to the server and get the response
    public static String sendCommand(String command) {
        try {
            out.println(command);  // Send the command to the server
            return in.readLine();  // Read and return the response from the server
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR: Could not communicate with server";
        }
    }

    // Getter methods for PrintWriter and BufferedReader
    public static PrintWriter getOut() {
        return out;
    }

    public static BufferedReader getIn() {
        return in;
    }

    // Method to close the client connection
    public void close() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

