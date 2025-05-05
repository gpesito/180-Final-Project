import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
/**
 * GUI version of the client to communicate with the server
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * @author Gabby Pesito
 * @version May 5, 2025
 */
public class AuctionApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Ask the user for the server address and port
            String address = JOptionPane.showInputDialog("Enter server address (e.g., localhost):");
            String portStr = JOptionPane.showInputDialog("Enter server port (e.g., 4242):");

            try {
                int port = Integer.parseInt(portStr);

                // Create the Client object and connect to the server
                Client client = new Client(address, port);

                // GUI launch
                AuctionApp auctionApp = new AuctionApp();  
                auctionApp.createAndShowGUI();  // Initialize GUI

            } catch (NumberFormatException e) {
                // Handle invalid port number input
                JOptionPane.showMessageDialog(null, "Invalid port number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Auction Marketplace");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        // Shared state
        final UserInterface[] currentUser = {null};

        // Create main panel and tabbed pane
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane tabs = new JTabbedPane();

        // Authentication tab
        JPanel authPanel = new JPanel(new GridLayout(2, 1));

        // Login Panel
        JPanel loginPanel = new JPanel();
        JTextField loginUser = new JTextField(10);
        JPasswordField loginPass = new JPasswordField(10);
        JButton loginBtn = new JButton("Login Returning User");
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUser);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPass);
        loginPanel.add(loginBtn);
        authPanel.add(loginPanel);

        loginBtn.addActionListener(e -> {
            String user = loginUser.getText();
            String pass = new String(loginPass.getPassword());
            sendCommand("LOGIN " + user + " " + pass, frame, currentUser);
        });

        // Register Panel
        JPanel regPanel = new JPanel();
        JTextField regId = new JTextField(8);
        JTextField regUsername = new JTextField(10);
        JTextField regEmail = new JTextField(12);
        JPasswordField regPass = new JPasswordField(10);
        JButton regBtn = new JButton("Register New User");

        regPanel.add(new JLabel("User ID:"));
        regPanel.add(regId);
        regPanel.add(new JLabel("Username:"));
        regPanel.add(regUsername);
        regPanel.add(new JLabel("Email:"));
        regPanel.add(regEmail);
        regPanel.add(new JLabel("Password:"));
        regPanel.add(regPass);
        regPanel.add(regBtn);
        authPanel.add(regPanel);

        // Action for Register Button
        regBtn.addActionListener(e -> {
            String userId = regId.getText();
            String username = regUsername.getText();
            String email = regEmail.getText();
            String password = new String(regPass.getPassword());

            User newUser = new User(userId, username, email, password);
            UserService.registerUser(newUser);

            sendCommand("REGISTER " + userId + " " + username + " " + email + " " + password, frame, currentUser);
            JOptionPane.showMessageDialog(frame, "New user registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        tabs.add("Auth", authPanel);

        JPanel productPanel = new JPanel(new BorderLayout());

        JPanel addProductPanel = new JPanel();
        JTextField pid = new JTextField(5), pname = new JTextField(10), pdesc = new JTextField(10),
                pprice = new JTextField(6), pcat = new JTextField(8);
        JButton addBtn = new JButton("Add Product");

        addProductPanel.add(new JLabel("ID:"));
        addProductPanel.add(pid);
        addProductPanel.add(new JLabel("Name:"));
        addProductPanel.add(pname);
        addProductPanel.add(new JLabel("Desc:"));
        addProductPanel.add(pdesc);
        addProductPanel.add(new JLabel("Price:"));
        addProductPanel.add(pprice);
        addProductPanel.add(new JLabel("Category:"));
        addProductPanel.add(pcat);
        addProductPanel.add(addBtn);

        productPanel.add(addProductPanel, BorderLayout.NORTH);

        addBtn.addActionListener(e -> {
            String productId = pid.getText();
            String productName = pname.getText();
            String productDescription = pdesc.getText();

            double price = 0.0;
            try {
                price = Double.parseDouble(pprice.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid price. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String category = pcat.getText();
            String sellerId = currentUser[0].getUserId();

            ProductService productService = new ProductService();
            productService.addProduct(productId, productName, productDescription, price, category);

            sendCommand("ADD_PRODUCT " + productId + " " + productName + " " + productDescription + " " + price + " " + category + " " + sellerId, frame, currentUser);
        });

        JPanel searchPanel = new JPanel();
        JTextField keywordField = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        JTextArea results = new JTextArea(10, 70);
        results.setEditable(false);
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(keywordField);
        searchPanel.add(searchBtn);
        productPanel.add(searchPanel, BorderLayout.CENTER);
        productPanel.add(new JScrollPane(results), BorderLayout.SOUTH);

        searchBtn.addActionListener(e -> {
            String keyword = keywordField.getText();
            ProductService newService = new ProductService();
            List<Product> products = (List<Product>) newService.searchProducts(keyword);
            String output = "";
            for (Product p : products) {
                output += "ID: " + p.getProductID() + ", Name: " + p.getName() + ", Price: " + p.getPrice() + "\n";
            }
            JOptionPane.showMessageDialog(frame, output.isEmpty() ? "No products found." : output, "Search Results", JOptionPane.INFORMATION_MESSAGE);
        });

        tabs.add("Products", productPanel);

        JPanel msgPanel = new JPanel(new BorderLayout());
        JPanel sendMsgPanel = new JPanel();
        JTextField toField = new JTextField(8), msgField = new JTextField(20), prodIdField = new JTextField(8);
        JButton sendBtn = new JButton("Send");

        sendMsgPanel.add(new JLabel("To User ID:"));
        sendMsgPanel.add(toField);
        sendMsgPanel.add(new JLabel("Product ID:"));
        sendMsgPanel.add(prodIdField);
        sendMsgPanel.add(new JLabel("Message:"));
        sendMsgPanel.add(msgField);
        sendMsgPanel.add(sendBtn);
        msgPanel.add(sendMsgPanel, BorderLayout.NORTH);

        JTextArea msgArea = new JTextArea(10, 70);
        msgArea.setEditable(false);
        JButton refreshBtn = new JButton("Refresh Inbox");
        msgPanel.add(new JScrollPane(msgArea), BorderLayout.CENTER);
        msgPanel.add(refreshBtn, BorderLayout.SOUTH);

        sendBtn.addActionListener(e -> {
            String senderId = currentUser[0].getUserId();
            String receiverId = toField.getText();
            String productId = prodIdField.getText();
            String messageContent = msgField.getText();
            LocalDateTime timestamp = LocalDateTime.now();
            String messageId = UUID.randomUUID().toString();
            Message newMessage = new Message(messageId, senderId, receiverId, productId, messageContent, timestamp);
            MessageService messageService = new MessageService();
            messageService.sendMessage(senderId, receiverId, messageContent, productId);
            sendCommand("SEND_MESSAGE " + senderId + " " + receiverId + " " + messageContent + " " + productId, frame, msgArea);
        });

        refreshBtn.addActionListener(e -> {
            sendCommand("GET_MESSAGES " + currentUser[0].getUserId(), frame, msgArea);
        });

        tabs.add("Messaging", msgPanel);

        JPanel txPanel = new JPanel(new BorderLayout());
        JPanel createTxPanel = new JPanel();
        JTextField sellerId = new JTextField(8), amountField = new JTextField(6);
        JButton createTxBtn = new JButton("Create Tx");
        createTxPanel.add(new JLabel("To Seller ID:"));
        createTxPanel.add(sellerId);
        createTxPanel.add(new JLabel("Amount:"));
        createTxPanel.add(amountField);
        createTxPanel.add(createTxBtn);
        txPanel.add(createTxPanel, BorderLayout.NORTH);

        JTextArea txList = new JTextArea(10, 70);
        txList.setEditable(false);
        JButton loadTxBtn = new JButton("View My Transactions");
        txPanel.add(new JScrollPane(txList), BorderLayout.CENTER);
        txPanel.add(loadTxBtn, BorderLayout.SOUTH);

        createTxBtn.addActionListener(e -> {
            String buyerId = currentUser[0].getUserId();
            String sellerIdText = sellerId.getText();

            double amount = 0.0;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Transaction newTransaction = new Transaction((User) UserService.getUserById(buyerId), (User) UserService.getUserById(sellerIdText), amount);
            TransactionService transactionService = new TransactionService(null, null, 0);
            transactionService.validateTransaction((User) UserService.getUserById(buyerId), (User) UserService.getUserById(sellerIdText), amount);
            transactionService.processTransaction((User) UserService.getUserById(buyerId), (User) UserService.getUserById(sellerIdText), amount);

            sendCommand("CREATE_TRANSACTION " + buyerId + " " + sellerIdText + " " + amount, frame, txList);
        });

        loadTxBtn.addActionListener(e -> {
            sendCommand("GET_TRANSACTIONS " + currentUser[0].getUserId(), frame, txList);
        });

        tabs.add("Transactions", txPanel);

        panel.add(tabs, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void sendCommand(String command, JFrame frame, JTextArea outputArea) {
        try {
            PrintWriter out = Client.getOut();
            BufferedReader in = Client.getIn();
            out.println(command);
            String response = in.readLine();

            if (response.startsWith("ERROR")) {
                JOptionPane.showMessageDialog(frame, "Error: " + response.substring(5), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                outputArea.setText(response);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error communicating with server.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendCommand(String command, JFrame frame, UserInterface[] currentUser) {
        try {
            PrintWriter out = Client.getOut();
            BufferedReader in = Client.getIn();

            out.println(command);
            String response = in.readLine();

            if (response.startsWith("ERROR")) {
                JOptionPane.showMessageDialog(frame, "Error: " + response.substring(5), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, response, "Success", JOptionPane.INFORMATION_MESSAGE);

                if (command.startsWith("LOGIN")) {
                    String[] userData = response.split(",");
                    if (userData.length >= 4) {
                        String userId = userData[0];
                        String username = userData[1];
                        String email = userData[2];
                        String password = userData[3];
                        currentUser[0] = new User(userId, username, email, password);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid response from server.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error communicating with server.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
