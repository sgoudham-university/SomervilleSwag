package org.somerville.Display;

import org.somerville.Data.Model.Customer;

import javax.swing.*;
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
     * @param oldframe
     * @param customer
     */

    public LandingPage(JFrame oldframe, Customer customer) {

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new SignUp(oldframe).root);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new LogIn(oldframe, customer).root);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });

        viewBasketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new Basket(oldframe, customer).root);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });
    }


    public static void main(String[] args) {
        Customer cust = null;

        JFrame frame = new JFrame("Somerville Swag");
        frame.setContentPane(new LandingPage(frame, cust).root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
