package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.source.SQLiteExecute;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            customer.signUp(root,
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtEmail.getText(),
                    String.valueOf(txtPassword.getPassword()),
                    String.valueOf(txtPasswordConfirm.getPassword()),
                    txtAddress1.getText(),
                    txtAddress2.getText(),
                    txtCity.getText(),
                    txtPostcode.getText(),
                    txtPhoneNo.getText());

            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

    }
}

