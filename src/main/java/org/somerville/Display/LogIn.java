package org.somerville.Display;

import org.somerville.Data.Model.Customer;

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

    public LogIn(JFrame oldframe, Customer customer) {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Somerville Swag");
                frame.setContentPane(new LandingPage(oldframe, customer).root);
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
            }
        });
    }
}
