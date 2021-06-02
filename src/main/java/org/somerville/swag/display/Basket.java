package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.OrderLine;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.entity.state.Guest;
import org.somerville.swag.data.entity.util.Common;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class Basket {
    private JTable tblBasket;
    public JPanel root;
    private JButton buyNowButton;
    private JButton backButton;
    private JLabel orderTotal;
    private JButton removeFromBasketButton;

    public Basket(JFrame oldFrame, Customer customer) {

        List<OrderLine> lines = customer.getCurrentOrder().getOrderLines();
        refreshTable(lines, customer);

        backButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("\uD83D\uDE0E✨ Somerville Swag ✨\uD83D\uDE0E", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        buyNowButton.addActionListener(actionEvent -> {
            List<OrderLine> customerBasket = customer.getCurrentOrder().getOrderLines();

            if (customer.getCustomerState() instanceof Guest) {
                Common.showMessage(root, "Not Swagged In \uD83E\uDD2F", "Uh Oh! Can't SwagOut When Not SwaggedIn", JOptionPane.ERROR_MESSAGE);
            } else if (customerBasket.isEmpty()) {
                Common.showMessage(root, "No Swag in Basket \uD83E\uDD2F", "No items in basket", JOptionPane.ERROR_MESSAGE);
            } else {
                new JFrameBuilder.Builder().buildDefaultJFrame("\uD83D\uDECD️\uD83D\uDCB2 Checkout \uD83D\uDCB2\uD83D\uDECD️", new Purchase(oldFrame, customer).root, true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });

        removeFromBasketButton.addActionListener(actionEvent -> {
            if (tblBasket.getModel().getRowCount() == 0) {
                Common.showMessage(root, "No Swag in Basket \uD83E\uDD2F", "Where's Your Swag at?", JOptionPane.ERROR_MESSAGE);
            }
            else if (tblBasket.getSelectedRow() == -1) {
                Common.showMessage(root, "SwagRow Not Found 404 \uD83E\uDD2F", "No SwagRow Selected", JOptionPane.ERROR_MESSAGE);
            } else {
                DefaultTableModel defaultTableModel = (DefaultTableModel) tblBasket.getModel();
                Vector vector = defaultTableModel.getDataVector().elementAt(tblBasket.getSelectedRow());
                OrderLine orderLine = new OrderLine((Product) vector.get(0), (Integer) vector.get(1));

                customer.removeProductFromBasket(root, orderLine);
                refreshTable(lines, customer);
            }
        });
    }

    private void refreshTable(List<OrderLine> lines, Customer customer) {
        String[] columnNames = {"Product" , "Quantity", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (OrderLine orderLine : lines) {
            Object[] newRow = { orderLine.getProduct(), orderLine.getQuantity(), orderLine.getTotalRowPrice() };
            model.addRow(newRow);
        }
        orderTotal.setText(customer.getCurrentOrder().getFormattedTotal());
        tblBasket.setRowHeight(25);
        tblBasket.setModel(model);
    }
}
