package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LogIn {
    public JPanel root;
    private JTextField txtPassword;
    private JTextField txtEmail;
    private JButton confirmButton;
    private JButton backButton;

    public LogIn(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            customer.logIn(root, txtEmail.getText(), txtPassword.getText());

            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });
    }
}
