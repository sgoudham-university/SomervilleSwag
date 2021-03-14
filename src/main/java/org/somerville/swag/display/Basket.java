package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;

public class Basket {
    private JTable tblBasket;
    public JPanel root;
    private JButton buyNowButton;
    private JButton backButton;

    public Basket(JFrame oldFrame, Customer customer) {

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        buyNowButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new Purchase(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });
    }

}
