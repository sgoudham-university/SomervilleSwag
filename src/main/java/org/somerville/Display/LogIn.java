package org.somerville.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn {
    public JPanel root;
    private JTextField textField2;
    private JTextField textField1;
    private JButton confirmButton;
    private JButton backButton;

    public LogIn(JFrame oldframe) {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Somerville Swag");
                frame.setContentPane(new LandingPage(frame).root);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });
    }
}
