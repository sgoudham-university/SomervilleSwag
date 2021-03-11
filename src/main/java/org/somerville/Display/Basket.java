package org.somerville.Display;

import org.somerville.Data.Model.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Basket {
    private JTable tblBasket;
    public JPanel root;
    private JButton buyNowButton;
    private JButton backButton;

    public Basket(JFrame oldframe, Customer customer) {
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

        buyNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Somerville Swag");
                frame.setContentPane(new Purchase(oldframe, customer).root);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });
    }

}
