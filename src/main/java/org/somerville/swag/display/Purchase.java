package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;

public class Purchase {
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton confirmButton;
    private JButton backButton;
    private JTextField textField2;
    public JPanel root;

    public Purchase(JFrame oldFrame, Customer customer) {
        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new Basket(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            customer.purchaseItems();
        });
    }
}
