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
    private JTextField txtForename;
    private JTextField txtAddressLine1;
    private JTextField txtAddressLine2;
    private JTextField txtPostcode;
    private JTextField txtCity;
    private JTextField txtSurname;

    public Purchase(JFrame oldFrame, Customer customer) {

        initialiseCheckout();
        setDefaultShippingAddress(customer);

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Your Basket", new Basket(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        confirmButton.addActionListener(actionEvent -> customer.purchaseItems(oldFrame, root, txtCardNo.getText(), txtCvv.getText()));
    }

    private void setDefaultShippingAddress(Customer customer) {
        this.txtForename.setText(customer.getForename());
        this.txtSurname.setText(customer.getSurname());
        this.txtAddressLine1.setText(customer.getAddressLine1());
        this.txtAddressLine2.setText(customer.getAddressLine2());
        this.txtPostcode.setText(customer.getPostcode());
        this.txtCity.setText(customer.getCity());
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
