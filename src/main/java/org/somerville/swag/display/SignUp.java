package org.somerville.swag.display;

import org.somerville.swag.data.entities.Customer;
import org.somerville.swag.data.entities.Order;

import javax.swing.*;
import java.awt.*;

public class SignUp {
    private JTextField textField1;
    private JTextField textField2;
    private JButton confirmButton;
    private JButton backButton;
    public JPanel root;

    public SignUp(JFrame oldframe, Customer customer, Order order) {

        // TODO: Not sure why this event doesn't follow the format of others? - Goudham
//        backButton.addActionListener(actionEvent -> {
//            oldframe.setVisible(true);
//            SwingUtilities.getWindowAncestor(root).dispose();
//        });

        backButton.addActionListener(actionEvent -> {
            Container contentPane = new LandingPage(oldframe, customer, order).root;
            new JFrameBuilder.Builder().buildDefaultJFrame(contentPane, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            /**
             * Needs to call create customer logic
             */

        });
    }
}
