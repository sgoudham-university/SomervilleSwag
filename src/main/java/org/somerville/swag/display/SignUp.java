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

    public SignUp(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            if(!txtFirstName.getText().strip().matches("[a-zA-Z]{1,15}") ||
                    !txtLastName.getText().strip().matches("[a-zA-Z]{1,15}") ||
                    !txtEmail.getText().strip().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$") ||
                    !String.valueOf(txtPassword.getPassword()).matches("(^?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$") ||
                    !String.valueOf(txtPasswordConfirm.getPassword()).matches("(^?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$") ||
                    !txtAddress1.getText().matches("^[a-zA-Z0-9_ ]*$") ||
                    !txtPhoneNo.getText().strip().matches("[0-9]{11}") &&
                    !String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtPasswordConfirm.getPassword()))
            )   //order of pressidence/logic for and and or inside the same ifs???
            {
                JOptionPane.showMessageDialog(root, "Something went wrong and the code is so bad I dont know what",
                        "Sign In error", JOptionPane.ERROR_MESSAGE);
                //regex expression for all txtFields
                //include J option Panes as error messages
                //database call once all validation is completed
            }
            customer.signUp();
        });

    }
}

