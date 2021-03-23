package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Order;
import org.somerville.swag.data.entity.OrderLine;
import org.somerville.swag.data.entity.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private JTable tblBasket;
    public JPanel root;
    private JButton buyNowButton;
    private JButton backButton;
    private JLabel orderTotal;

    public Basket(JFrame oldFrame, Customer customer) {

        /**
         * Load and display pretend order at page construction
         */

        //STUB
        Product productA = new Product(0,"Product A","Description A", BigDecimal.valueOf(19.99), 5, "Path");
        Product productB = new Product(1,"Product B","Description B", BigDecimal.valueOf(29.99), 10, "Path");
        Product productC = new Product(2,"Product C","Description C", BigDecimal.valueOf(29.99), 10, "Path");
        OrderLine lineA = new OrderLine(productA, 1);
        OrderLine lineB = new OrderLine(productB, 5);
        OrderLine lineC = new OrderLine(productC, 5);

        Customer c = new Customer();
        Order o = c.getCurrentOrder();
        o.add(lineA);
        o.add(lineB);
        for(int i = 0; i<70;i++){
            o.add(lineC);
        }
        //End STUB


        List<OrderLine> lines = o.getOrderLines(); //replace o.getOrderLines() with customer.getOrder().getOrderLinesAsList()

        //---------------------TABLE LAYOUT-----------------------------------------------------------------------------

        String[] columNames = {"Product" , "Quantity", "Price"};
        DefaultTableModel model = new DefaultTableModel(columNames,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (OrderLine orderLine : lines) {
            Object[] newRow = { orderLine.getProductId(), orderLine.getQuantity(), orderLine.getProductId().getFormattedPrice()};
            model.addRow(newRow);
        }                           //we can set the order total in here also
        orderTotal.setText(o.getFormattedTotal());
        tblBasket.setModel(model);



        //---------------------END TABLE LAYOUT-------------------------------------------------------------------------

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
