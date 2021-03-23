package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.source.SQLiteExecute;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SignUp {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField7;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    private JButton confirmButton;
    private JButton backButton;
    public JPanel root;
    private JTextField textField5;
    private JLabel lblAddressLineTwo;
    private JLabel lblPhoneNumber;
    private JLabel lblAddressLineOne;
    private JLabel lblConfirmPassword;
    private JLabel lblPassword;
    private JLabel lblEmailAddress;
    private JLabel lblSurname;
    private JLabel lblForename;

    public SignUp(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> customer.signUp());


    }
}

