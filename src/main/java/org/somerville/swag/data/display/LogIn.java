package org.somerville.swag.data.display;

import org.somerville.swag.data.entities.Customer;
import org.somerville.swag.data.entities.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Currency;

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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Somerville Swag");
                frame.setContentPane(new LandingPage(oldframe, customer, order).root);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * needs to call create customer logic
                 */
                if(!customer.equals(null)){
                    customer.equals(null);
                }
                else {
                    //customer = *todologic*
                }
            }
        });
    }
}
