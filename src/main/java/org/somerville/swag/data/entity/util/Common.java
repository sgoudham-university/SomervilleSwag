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

    private static final DBSource dbSource = new SQLiteSource();

    /**
     * Allows the Customer to add products to their basket, up to the current stock level.
     * The product (if found within the customer basket) is removed and then added with the updated stock level.
     * When no existing product is found, it is added into the Customer Basket.
     *
     * All actions involve updating the product stock level within the database.
     *
     * @param root The root panel of the Display JFrame
     * @param customer The current Customer logged in
     * @param product The product that will be added to the Customer Basket
     * @param quantity The amount of product the Customer wants to add to their basket
     */
    public static void addProductToBasket(JPanel root, Customer customer, Product product, int quantity) {
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

    /**
     * Allows the Customer to remove products from their basket.
     * The product is removed from the customer basket and the product stock level is updated within the database
     *
     * @param customer The current Customer logged in
     * @param orderLine The OrderLine that will be removed from the Customer's Order
     */
    public static void removeProductFromBasket(Customer customer, OrderLine orderLine) {
        Product selectedProduct = orderLine.getProduct();
        int selectedProductQuantity = orderLine.getQuantity();
        Order customerOrder = customer.getCurrentOrder();
        List<OrderLine> customerOrderLines = customerOrder.getOrderLines();

        customerOrderLines.remove(orderLine);
        selectedProduct.setStockLevel(selectedProduct.getStockLevel() + selectedProductQuantity);

        dbSource.updateProductStockLevel(selectedProduct.getProductId(), selectedProduct.getStockLevel());
    }

    public static void showMessage(JPanel root, String message, String title, int type) {
        JOptionPane.showMessageDialog(root, message, title, type);
    }
}
