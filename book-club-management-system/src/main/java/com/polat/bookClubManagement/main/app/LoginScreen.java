package com.polat.bookClubManagement.main.app;

import com.polat.bookClubManagement.main.lib.database.DatabaseConnection;
import com.polat.bookClubManagement.main.lib.database.UserCredentialDAL;
import com.polat.bookClubManagement.main.lib.wrappers.AuthenticationManagerWrapper;
import com.polat.bookClubManagement.umple.MemberManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private AuthenticationManagerWrapper authManagerWrapper;
    private UserCredentialDAL userCredentialDAL;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginScreen frame = new LoginScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 455, 335);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        initializeComponents();

        // Veritabanı bağlantısını oluştur
        Connection connection = createDatabaseConnection();

        if (connection != null) {
            // MemberManagement örneğini oluştur
            MemberManagement memberManagement = new MemberManagement();

            // AuthenticationManagerWrapper örneğini oluştur
            authManagerWrapper = new AuthenticationManagerWrapper(memberManagement);

            // UserCredentialDAL örneğini oluştur
            userCredentialDAL = new UserCredentialDAL(connection);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    handleLogin(username, password);
                }
            });

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    handleRegister(username, password);
                }
            });
        } else {
            JOptionPane.showMessageDialog(contentPane, "Failed to connect to the database.");
        }
    }

    private void initializeComponents() {
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 165, 100, 13);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 157, 223, 29);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(null);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        contentPane.add(passwordPanel);
        
                usernameField = new JTextField(20);
                usernameField.setBounds(150, 112, 223, 29);
                passwordPanel.add(usernameField);
                JLabel usernameLabel = new JLabel("Username:");
                usernameLabel.setBounds(40, 120, 100, 13);
                passwordPanel.add(usernameLabel);
                registerButton = new JButton("Register");
                registerButton.setBounds(251, 218, 95, 31);
                passwordPanel.add(registerButton);
                loginButton = new JButton("Login");
                loginButton.setBounds(85, 218, 95, 31);
                passwordPanel.add(loginButton);
                
                Label label = new Label("Book Club Management System");
                label.setBounds(21, 27, 372, 53);
                passwordPanel.add(label);
                label.setFont(new Font("Arial", Font.BOLD, 18));
                label.setAlignment(Label.CENTER);
                label.setBackground(new Color(255, 255, 255));
                
                ImageIcon icon2 = new ImageIcon(LoginScreen.class.getResource("image.jpg"));
        		JLabel label_loginbackground = new JLabel("");
        		label_loginbackground.setBounds(0, 0, 826, 508);
        		Image image2 = icon2.getImage().getScaledInstance(label_loginbackground.getWidth(),
        		label_loginbackground.getHeight(), Image.SCALE_SMOOTH);
        		ImageIcon iccon = new ImageIcon(image2);
        		label_loginbackground.setIcon(iccon);
        		passwordPanel.add(label_loginbackground);
    }

    private void handleLogin(String username, String password) {
        boolean isAuthenticated = userCredentialDAL.loginUser(username, password);
        if (isAuthenticated) {
            JOptionPane.showMessageDialog(contentPane, "Login successful!");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new HomePage(username).setVisible(true);
                    dispose();
                }
            });
        } else {
            JOptionPane.showMessageDialog(contentPane, "Invalid username or password!");
        }
    }

    private void handleRegister(String username, String password) {
        boolean isRegistered = userCredentialDAL.registerUser(username, password);
        if (isRegistered) {
            JOptionPane.showMessageDialog(contentPane, "Registration successful!");
        } else {
            JOptionPane.showMessageDialog(contentPane, "Registration failed!");
        }
    }

    private Connection createDatabaseConnection() {
        try {
            return DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
