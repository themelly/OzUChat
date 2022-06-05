package tr.edu.ozyegin.chat.client;

import java.awt.*;
import java.awt.Color;

import tr.edu.ozyegin.chat.messages.LoginRequest;
import tr.edu.ozyegin.chat.messages.LoginResponse;
import tr.edu.ozyegin.chat.messages.MessageHistoryResponse;
import tr.edu.ozyegin.chat.messages.MessageResponse;
import tr.edu.ozyegin.chat.messages.PersonListRequest;
import tr.edu.ozyegin.chat.messages.PersonListResponse;

public class ChatClientGUI {

    private static MockChatClient client;
    private String username;
    private String password;
    private Frame frame;
    private LoginScreen loginScreen;

    ChatClientGUI() {
        
        this.username = "";
        this.password = "";

        String duplicateUsername = username;

        this.client = new MockChatClient();

        /* Create and set up the frame */
        this.frame = new Frame("OzU Chat");
        Button connectButton = new Button("Connect");
        Button sendButton = new Button("SEND");
        TextField messageInput = new TextField();
        TextArea messageLog = new TextArea();
        Color c = new Color(96, 164, 236);
        Color messageList = new Color(189, 189, 189, 255);
        TextArea userList = new TextArea();
        Label label = new Label("  You are disconnected from OzU Chat");

        /* Material sizing and positioning */
        frame.setLayout(new BorderLayout());
        messageLog.setBounds(30, 150, 370, 510);
        messageLog.setEditable(false);
        messageLog.setSize(370, 510);
        messageInput.setBounds(30, 690, 370, 80);
        sendButton.setBounds(410, 690, 170, 80);
        connectButton.setBounds(250, 70, 190, 40);
        label.setBounds(5, 30, 590, 100);
        userList.setBounds(410, 150, 170, 510);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        /* Frame settings */
        frame.add(messageInput);
        frame.add(sendButton);
        frame.add(connectButton);
        frame.add(messageLog);
        frame.add(userList);
        frame.add(label);

        frame.setSize(600, 800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBackground(c);

        /* Label settings */
        label.setAlignment(Label.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 12));

        /* User List editable/uneditable */
        userList.setEditable(false);

        /* Text Editable/Uneditable */
        messageLog.setEditable(false);
        messageInput.setEditable(false);
        messageInput.setBackground(messageList);
        messageLog.setBackground(messageList);
        userList.setBackground(messageList);

        /* Chatting enable/disable with connect button */
        connectButton.addActionListener(e -> {
            if (connectButton.getLabel().equals("Connect")) {
                LoginScreen loginScreen = new LoginScreen();
                
				while (username.equals("") && password.equals("")) {
                    this.username = loginScreen.usernamee;
                    this.password = loginScreen.passwordd;

                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.username = username;
                    loginRequest.password = password;
                    System.out.println("x");
                    client.sendMessage(loginRequest);

                    messageInput.setEditable(false);
                    sendButton.setEnabled(false);

                    if (!(username.equals("") || password.equals("null"))) {
                        userList.append(" " + username + "\n");
                        }
                }

                messageInput.setEditable(true);
                sendButton.setEnabled(true);
                connectButton.setLabel("Disconnect");
                messageInput.setBackground(messageList);
                messageLog.setBackground(messageList);
                userList.setBackground(messageList);

            } else {
                messageInput.setEditable(true);
                sendButton.setEnabled(true);
                messageInput.setBackground(Color.WHITE);
                messageLog.setBackground(Color.WHITE);
                userList.setBackground(Color.WHITE);
                connectButton.setLabel("Connect");
            }

        });

        /*
         * When the label of connect button is changed, the label of the label is
         * changed too.
         */
        connectButton.addActionListener(e -> {
            if (connectButton.getLabel().equals("Disconnect")) {
                label.setText("   \u2713 You are connected to OzU Chat");

            } else {
                label.setText("   You are disconnected from OzU Chat");

            }
        });

        /* Send button action */
        sendButton.addActionListener(e -> {
            String message = messageInput.getText();
            if (message.length() > 0) {
                messageInput.setText("");
                messageInput.requestFocus();
                messageLog.append("You: " + message + "\n");
            }
        });
        /* Sending messages with enter key */
        messageInput.addActionListener(e -> {
            String message = messageInput.getText();
            if (message.length() > 0) {
                messageLog.append("You: " + message + "\n");
                messageInput.setText("");
            }
        });

        client.registerChatMessageListener(new ChatMessageListener() {

            private String username = duplicateUsername;
            private Color usernameColor = new Color(198, 198, 198, 255);

            @Override
            public void personListResponseReceived(PersonListResponse personListResponse) {
                for (String s : personListResponse.personList) {
                    try {
                        if (s.equals(username)) {
                            userList.append(" (You) " + s.substring(0, 1).toUpperCase() + s.substring(1) + "\n");
                        } else {
                            userList.append(" " + s.substring(0, 1).toUpperCase() + s.substring(1) + "\n");
                        }
                    } catch (Exception e) {
                        continue;
                    }

                }
            }

            @Override
            public void loginResponseReceived(LoginResponse loginResponse) {
                if (loginResponse.success) {
                    messageLog.append("Login Successful!" + "\n");
                }
            }

            @Override
            public void messageResponseReceived(MessageResponse messageResponse) {
                messageLog.append("[" + messageResponse.time + "] " + messageResponse.sender + " : "
                        + messageResponse.message + "\n");

            }

            @Override
            public void messageHistoryResponseReceived(MessageHistoryResponse messageHistoryResponse) {
                messageLog.append("PREVIOUS MESSAGES" + "\n");

                for (MessageResponse m : messageHistoryResponse.messages) {
                    messageResponseReceived(m);
                }

            }

        });

        this.client.connect("localhost", 7777);

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.username = this.username;
        loginRequest.password = this.password;

        client.sendMessage(loginRequest);

        PersonListRequest personListRequest = new PersonListRequest();

        client.sendMessage(personListRequest);
    }

    public void setVisible(boolean setVisible) {
        this.frame.setVisible(setVisible);
    }

}
