package org.somerville.Display;

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

    public LandingPage(JFrame oldframe) {
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
                frame.setContentPane(new LogIn(oldframe).root);
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
                frame.setContentPane(new Basket(oldframe).root);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });
    }

    /**
     * We're going to dispose and rebuild each frame as required
     *
     * need a method to take in data on frame rebuild
     */

    public static void main(String[] args) {
        JFrame frame = new JFrame("Somerville Swag");
        frame.setContentPane(new LandingPage(frame).root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
