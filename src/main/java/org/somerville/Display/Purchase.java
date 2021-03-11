package org.somerville.Display;

import org.somerville.Data.Model.Customer;
import org.somerville.Data.Model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Purchase {
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton confirmButton;
    private JButton backButton;
    private JTextField textField2;
    public JPanel root;

    public Purchase(JFrame oldframe, Customer customer, Order order) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Somerville Swag");
                frame.setContentPane(new Basket(oldframe, customer, order).root);
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
                 * needs to call card validation logic
                 */
            }
        });
    }
}
