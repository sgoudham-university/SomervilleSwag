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
            if(!txtFirstName.getText().strip().matches("[a-zA-Z]{1,15}") || //only characters, min length 1 - max length 15
                    !txtLastName.getText().strip().matches("[a-zA-Z]{1,15}") || //only characters, min length 1 - max length 15
                    !txtEmail.getText().strip().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$") || //Simple email expression. Doesn't allow numbers in the domain name and doesn't allow for top level domains that are less than 2 or more than 3 letters (which is fine until they allow more). Doesn't handle multiple &quot;.&quot; in the domain (joe@abc.co.uk).
                    !String.valueOf(txtPassword.getPassword()).matches("^[a-zA-Z]\\w{3,14}$") || //The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be use
                    !String.valueOf(txtPasswordConfirm.getPassword()).matches("^[a-zA-Z]\\w{3,14}$") || //The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be used
                    !txtAddress1.getText().matches("^[a-zA-Z0-9_ ]*$") || //no special characters, whisepace premitted
                    !txtPhoneNo.getText().strip().matches("[0-9]{11}") && //only numbers,, 11 characters
                    !String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtPasswordConfirm.getPassword())) //both password fields must match
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

