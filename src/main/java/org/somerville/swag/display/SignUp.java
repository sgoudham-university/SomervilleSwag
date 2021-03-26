package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;
import java.util.ArrayList;

public class SignUp {
    private JTextField txtLastName;
    private JTextField txtPhoneNo;
    private JTextField txtEmail;
    private JTextField txtAddress1;
    private JTextField txtAddress2;
    private JTextField txtFirstName;
    private JPasswordField txtPassword;
    private JPasswordField txtPasswordConfirm;

    private JButton confirmButton;
    private JButton backButton;
    public JPanel root;
    private JLabel lblAddressLineTwo;
    private JLabel lblPhoneNumber;
    private JLabel lblAddressLineOne;
    private JLabel lblConfirmPassword;
    private JLabel lblPassword;
    private JLabel lblEmailAddress;
    private JLabel lblSurname;
    private JLabel lblForename;
    private JTextField txtCity;
    private JTextField txtPostcode;

    public SignUp(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("\uD83D\uDE0E✨ Somerville Swag ✨\uD83D\uDE0E", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> customer.signUp(root, oldFrame, new ArrayList<>() {{
            add(txtFirstName.getText());
            add(txtLastName.getText());
            add(txtEmail.getText());
            add(String.valueOf(txtPassword.getPassword()));
            add(String.valueOf(txtPasswordConfirm.getPassword()));
            add(txtAddress1.getText());
            add(txtAddress2.getText());
            add(txtCity.getText());
            add(txtPostcode.getText());
            add(txtPhoneNo.getText());
        }}));

    }
}

