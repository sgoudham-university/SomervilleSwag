package org.somerville.swag.data.entity.util;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Order;
import org.somerville.swag.data.entity.OrderLine;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.source.DBSource;
import org.somerville.swag.data.source.SQLiteSource;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

public class Common {

    private final DBSource dbSource = new SQLiteSource();

    public void addProductToBasket(JPanel root, Customer customer, Product product, int quantity) {
        if (quantity == 0) {
            showMessage(root, "No Swag", "Your Quantity Of Swag Is Below The Minimum Swag Value",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            Order customerOrder = customer.getCurrentOrder();
            Iterator<OrderLine> customerOrderIterator = customerOrder.getOrderLines().iterator();
            int productPreviousStockLevel = product.getStockLevel();

            while (customerOrderIterator.hasNext()) {
                OrderLine orderLine = customerOrderIterator.next();
                Product productInBasket = orderLine.getProduct();

                if (productInBasket.equals(product)) {
                    productPreviousStockLevel += orderLine.getQuantity();
                    quantity += orderLine.getQuantity();

                    customerOrderIterator.remove();
                }
            }
            int productNewStockLevel = productPreviousStockLevel - quantity;
            product.setStockLevel(productNewStockLevel);

            customerOrder.add(new OrderLine(product, quantity));
            dbSource.updateProductStockLevel(product.getProductId(), productNewStockLevel);
        }
    }

    public void removeProductFromBasket(Customer customer, OrderLine orderLine) {
        Product selectedProduct = orderLine.getProduct();
        int selectedProductQuantity = orderLine.getQuantity();
        Order customerOrder = customer.getCurrentOrder();
        List<OrderLine> customerOrderLines = customerOrder.getOrderLines();

        customerOrderLines.remove(orderLine);
        selectedProduct.setStockLevel(selectedProduct.getStockLevel() + selectedProductQuantity);

        dbSource.updateProductStockLevel(selectedProduct.getProductId(), selectedProduct.getStockLevel());
    }

    private void showMessage(JPanel root, String message, String title, int type) {
        JOptionPane.showMessageDialog(root, message, title, type);
    }
}
