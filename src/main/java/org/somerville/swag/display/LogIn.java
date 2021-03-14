package org.somerville.swag.display;

import org.somerville.swag.data.entities.Customer;
import org.somerville.swag.data.entities.Order;

import javax.swing.*;
import java.awt.*;

public class LogIn {
    public JPanel root;
    private JTextField textField2;
    private JTextField textField1;
    private JButton confirmButton;
    private JButton backButton;

    public LogIn(JFrame oldframe, Customer customer, Order order) {

        if(customer != null){
            confirmButton.setText("Sign Out");
        }

        backButton.addActionListener(actionEvent -> {
            Container contentPane = new LandingPage(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            /**
             * needs to call create customer logic
             */
            if(!customer.equals(null)){
                customer.equals(null);
            }
            else {
                //customer = *todologic*
            }
        });
    }
}
