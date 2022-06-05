package tr.edu.ozyegin.chat.client;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import tr.edu.ozyegin.chat.messages.*;


public class LoginScreen extends Frame implements ActionListener {


public static final String usernamee = null;

public String username, password;
        
private Frame frame;
private boolean loginStatus;

TextField usernameText, passwordText;
Button loginButton, cancelButton;

public String passwordd;

LoginScreen() {
        
        this.loginStatus = false;
        
        
        this.username = ""; 
        this.password = "";

        this.frame = new Frame("Connect");
      
        loginButton = new Button("Connect");
        cancelButton = new Button("Cancel");
        
        usernameText = new TextField();
        passwordText = new TextField(); 
        
        Label usernameLabel = new Label("   Username:");
        Label passwordLabel = new Label("   Password:");
        
        Color c = new Color(96, 164, 236);
        
        MockChatClient client = new MockChatClient();


        
        /* Material sizing and positioning */
        frame.setLayout(new BorderLayout());
        usernameLabel.setBounds(10, 80, 100, 30);
        usernameText.setBounds(120, 80, 240, 30);
        passwordLabel.setBounds(10, 130, 100, 30);
        passwordText.setBounds(120, 130, 240, 30);
        loginButton.setBounds(70, 180, 100, 30);
        cancelButton.setBounds(220, 180, 100, 30);
        
        

        /* Password Field settings */
        passwordText.setEchoChar('*');


        
        /* Label settings */
        usernameLabel.setAlignment(Label.LEFT);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordLabel.setAlignment(Label.LEFT);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameLabel.setBackground(c);
        passwordLabel.setBackground(c);


        /* Frame settings */
        frame.add(usernameLabel);
        frame.add(usernameText);
        frame.add(passwordLabel);
        frame.add(passwordText);
        frame.add(loginButton);
        frame.add(cancelButton);
        

        frame.setSize(400, 250);

        frame.setLayout(null);

        frame.setVisible(true);

        frame.setBackground(c);
        /** loginButton should take text from usernameText and passwordText and setLoginStatus to true */
        
        /*
        loginButton.addActionListener(e -> {
            this.username = usernameText.getText();
            this.password = passwordText.getText();
            loginStatus = true;
            frame.setVisible(false);
        });
        */

        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        
       
       
        
    
        /*  
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
            setUsername(usernameText.getText());
            setPassword(passwordText.getText());
             
            LoginRequest loginRequest = new LoginRequest();
            
            loginRequest.username = usernameText.getText();
            loginRequest.password = passwordText.getText();
            
            setLoginStatus(true);
            
            client.connect("localhost", 7777);
            client.sendMessage(loginRequest);
            frame.setVisible(false);
            }
        });
        */
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
       
        cancelButton.addActionListener(arg0 -> {
            System.exit(0);
        });
            
        /*loginButton.addActionListener(e -> {
          
            });
        */

          
        
    
}   

public void actionPerformed(ActionEvent e) {
    String s1 = usernameText.getText();
    String s2 = passwordText.getText();
    String usernamee = String.valueOf(s1);
    String passwordd = String.valueOf(s2);
}


        public void setLoginStatus(boolean isLoginSuccessful) {
            this.loginStatus = isLoginSuccessful;
        }
        public boolean getLoginStatus() {
            return this.loginStatus;
        }
        
        public String getPassword() {
            return this.password;
        }        
        
        public String getUsername() {
            return this.username;
        }
       
        public void setUsername(String usernamee) {
            this.username = usernamee;
        }
        
        public void setPassword(String passwordd) {
            this.password = passwordd;
        }

        

public static void main(String[] args) {
    new LoginScreen();
}
        
        
}