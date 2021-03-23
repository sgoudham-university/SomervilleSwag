package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class Purchase {
    private JTextField txtCardNo;
    private JComboBox comMonth;
    private JComboBox comYear;
    private JButton confirmButton;
    private JButton backButton;
    private JTextField txtCvv2;
    public JPanel root;

    public Purchase(JFrame oldFrame, Customer customer) {

        int year = LocalDateTime.now().getYear();
        for( int i = 0; i < 6; i++){
            comYear.addItem(year + i);
        }
        for(int j = 1; j <= 12; j++){
            comMonth.addItem( j );
        }

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new Basket(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            customer.purchaseItems();

             if (!txtCardNo.getText().strip().matches("[0-9]{16}") || !txtCvv2.getText().strip().matches("[0-9]{3}")){
                JOptionPane.showMessageDialog(root, "Incorrect Card Number \n-Format as 1234123412341234 \n-Format as 123",
                        "Card Number Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(root, "Your Swag will be with you as soon as possible :)","Swag Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
