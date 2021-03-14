package org.somerville.swag.display;

import org.somerville.swag.data.entities.Customer;
import org.somerville.swag.data.entities.Order;

import javax.swing.*;
import java.awt.*;
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
        backButton.addActionListener(actionEvent -> {
            Container contentPane = new Basket(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, root);
        });

        confirmButton.addActionListener(actionEvent -> {
            /**
             * needs to call card validation logic
             */
        });
    }
}
