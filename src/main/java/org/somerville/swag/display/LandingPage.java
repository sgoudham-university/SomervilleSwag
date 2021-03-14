package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JSpinner;

public class LandingPage {
    private JButton signUpButton;
    private JButton logInButton;
    private JButton viewBasketButton;
    private JButton addToBasketButton;
    public JPanel root;
    private JList listOfProducts;
    private JPanel imagePanel;
    private JSpinner quantitySpinner;

    public LandingPage(JFrame oldFrame, Customer customer) {

        signUpButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new SignUp(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        logInButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new LogIn(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        viewBasketButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new Basket(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        listOfProducts.addListSelectionListener(e -> {

        });
    }


    public static void main(String[] args) {
        Customer customer = new Customer();
        new JFrameBuilder.Builder().buildDefaultJFrame(new LandingPage(new JFrame(), customer).root, true);
    }
}
