package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;

public class LogIn {
    public JPanel root;
    private JTextField txtEmail;
    private JButton confirmButton;
    private JButton backButton;
    private JPasswordField txtPassword;

    public LogIn(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("\uD83D\uDE0E✨ Somerville Swag ✨\uD83D\uDE0E", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            customer.logIn(root, txtEmail.getText(), String.valueOf(txtPassword.getPassword()));

            new JFrameBuilder.Builder().buildDefaultJFrame("\uD83D\uDE0E✨ Somerville Swag ✨\uD83D\uDE0E", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });
    }
}
