package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LogIn {
    public JPanel root;
    private JTextField textField2;
    private JTextField textField1;
    private JButton confirmButton;
    private JButton backButton;

    public LogIn(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> customer.logIn());
    }
}
