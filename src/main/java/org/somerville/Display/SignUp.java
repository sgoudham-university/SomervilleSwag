package org.somerville.Display;

import org.somerville.Data.Model.Customer;
import org.somerville.Data.Model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp {
    private JTextField textField1;
    private JTextField textField2;
    private JButton confirmButton;
    private JButton backButton;
    public JPanel root;

    public SignUp(JFrame oldframe, Customer customer, Order order) {


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldframe.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Needs to call create customer logic
                 */

            }
        });
    }
}
