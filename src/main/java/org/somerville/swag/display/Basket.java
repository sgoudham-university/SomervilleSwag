package org.somerville.swag.display;

import org.somerville.swag.data.entities.Customer;
import org.somerville.swag.data.entities.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Basket {
    private JTable tblBasket;
    public JPanel root;
    private JButton buyNowButton;
    private JButton backButton;

    public Basket(JFrame oldframe, Customer customer, Order order) {
        backButton.addActionListener(actionEvent -> {
            Container contentPane = new LandingPage(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, root);
        });

        buyNowButton.addActionListener(actionEvent -> {
            Container contentPane = new Purchase(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, root);
        });
    }

}
