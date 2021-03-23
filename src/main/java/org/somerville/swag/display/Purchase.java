package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class Purchase {
    private JTextField textField1;
    private JComboBox comMonth;
    private JComboBox comYear;
    private JButton confirmButton;
    private JButton backButton;
    private JTextField textField2;
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
        });
    }
}
