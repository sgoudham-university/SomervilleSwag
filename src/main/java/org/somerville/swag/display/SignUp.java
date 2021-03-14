package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;

public class SignUp {
    private JTextField textField1;
    private JTextField textField2;
    private JButton confirmButton;
    private JButton backButton;
    public JPanel root;

    public SignUp(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> customer.signUp());
    }
}
