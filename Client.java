import java.io.*;
import java.net.Socket;

/**
 * The Client class is responsible for interacting with the server
 * It sends commands to the server and receives responses
 * It acts as the client-side application in a client-server architecture
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * @author Milica Slavkovic
 * @version April 20, 2025
 */

public class Client {
    public static void main(String[] args) {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            // Prompt for server address and port
            System.out.print("Enter server address (e.g., localhost): ");
            String serverAddress = consoleReader.readLine();

            System.out.print("Enter server port (e.g., 4242): ");
            int serverPort = Integer.parseInt(consoleReader.readLine());

            try (Socket socket = new Socket(serverAddress, serverPort);
                 BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                System.out.println("Connected to the server.");
                String userCommand;

                // Continuously read user input and send it to the server
                while (true) {
                    System.out.println("Enter a command (ADD, DELETE, SEARCH_CATEGORY, SEARCH_KEYWORD, EXIT): ");
                    userCommand = userInput.readLine();

                    if (userCommand != null) {
                        out.println(userCommand);  // Send the command to the server

                        if (userCommand.equalsIgnoreCase("EXIT")) {
                            System.out.println("Exiting...");
                            break;
                        }

                        // Read and print the server's response
                        String serverResponse;
                        while ((serverResponse = in.readLine()) != null) {
                            System.out.println(serverResponse);
                            // Stop if response ends (optional: can set end marker from server if needed)
                            if (serverResponse.isEmpty()) break;
                        }
                    }
                }

            } catch (IOException e) {
                System.err.println("Connection failed: " + e.getMessage());
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }
}
