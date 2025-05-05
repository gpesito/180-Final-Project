import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

/**
 * GUI version of the client to communicate with the server
 * LEGACY CLASS FROM PHASES 1/2
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * @author Milica Slavkovic
 * @version April 25, 2025
 

public class ClientAlt {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JFrame frame;
    private JTextField commandField;
    private JTextArea outputArea;

    public Client(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            createAndShowGUI();
        } catch (IOException e) {
            showError("Failed to connect to server: " + e.getMessage());
        }
    }
    
    // Sets up the JFrame and its components
    private void createAndShowGUI() {
        frame = new JFrame("Product Client GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        commandField = new JTextField();
        JButton sendButton = new JButton("Send");
        outputArea = new JTextArea();
        outputArea.setEditable(false); // Read-only text area

        sendButton.addActionListener(this::handleSend); // Send command on click

        // Layout panel for input field and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(commandField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(outputArea), BorderLayout.CENTER);

        frame.setVisible(true); // Shows window
    }
    
    // Called when the Send button is clicked
    private void handleSend(ActionEvent event) {
        String command = commandField.getText().trim();
        if (command.isEmpty()) return;

        out.println(command);
        
        // If the user typed "EXIT", close everything and quit
        if (command.equalsIgnoreCase("EXIT")) {
            try {
                socket.close();
                frame.dispose();
            } catch (IOException e) {
                showError("Error while closing connection.");
            }
            return;
        }

        try {
            // After reading server response, displays it in the output area
            String response;
            outputArea.append("> " + command + "\n"); // Echo command
            while ((response = in.readLine()) != null) {
                outputArea.append(response + "\n");
                if (response.isEmpty()) break; // GUI expects empty line to signal end
            }
            outputArea.append("\n");
        } catch (IOException e) {
            showError("Error reading response: " + e.getMessage());
        }

        commandField.setText("");
    }
    
    // Method that shows an error dialog and close the app
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        if (frame != null) frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String address = JOptionPane.showInputDialog("Enter server address (e.g., localhost):");
            String portStr = JOptionPane.showInputDialog("Enter server port (e.g., 4242):");

            try {
                int port = Integer.parseInt(portStr);
                new Client(address, port);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid port number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

*/