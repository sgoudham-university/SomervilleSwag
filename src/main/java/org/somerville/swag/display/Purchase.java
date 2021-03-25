package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;

import javax.swing.*;
import java.time.LocalDateTime;

public class Purchase {
    private JTextField txtCardNo;
    private JComboBox<Integer> comMonth;
    private JComboBox<Integer> comYear;
    private JButton confirmButton;
    private JButton backButton;
    public JPanel root;
    private JTextField txtCvv;

    public Purchase(JFrame oldFrame, Customer customer) {

        initialiseCheckout();

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Your Basket", new Basket(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> {
            customer.purchaseItems(root, txtCardNo.getText(), txtCvv.getText());
            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });
    }

    private void initialiseCheckout() {
        int year = LocalDateTime.now().getYear();
        for( int i = 0; i < 6; i++){
            comYear.addItem(year + i);
        }
        for(int j = 1; j <= 12; j++){
            comMonth.addItem( j );
        }
    }
}
