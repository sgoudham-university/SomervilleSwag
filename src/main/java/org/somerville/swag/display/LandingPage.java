package org.somerville.swag.display;

import org.somerville.swag.data.entities.Customer;
import org.somerville.swag.data.entities.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage {
    private JButton signUpButton;
    private JButton logInButton;
    private JButton viewBasketButton;
    private JButton addToBasketButton;
    public JPanel root;
    private JList ListOfProducts;
    private JPanel ImagePanel;
    private JSpinner QuantitySpinner;

    /**
     * We are going to keep oldframe as the anchor point for the data, e.g. keeping the order lines and customer details
     * Customer needs to be passed between frames so we can track if they are logged in or not
     * We will pass order between frames so it is tracked through navigation
     * @param oldframe
     * @param customer
     * @param order
     */

    public LandingPage(JFrame oldframe, Customer customer, Order order) {

        signUpButton.addActionListener(actionEvent -> {
            Container contentPane = new SignUp(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, root);
        });

        logInButton.addActionListener(actionEvent -> {
            Container contentPane = new LogIn(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, root);
        });

        viewBasketButton.addActionListener(actionEvent -> {
            Container contentPane = new Basket(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, root);
        });
    }


    public static void main(String[] args) {
        Customer cust = null;
        Order order = null;

        Container contentPane = new LandingPage(new JFrame(), cust, order).root;
        new JFrameBuilder.Builder().buildDefaultJFrame(contentPane);
    }
}
