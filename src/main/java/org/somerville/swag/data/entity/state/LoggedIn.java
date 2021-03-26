package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.*;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;
import org.somerville.swag.data.source.DBSource;
import org.somerville.swag.data.source.SQLiteSource;
import org.somerville.swag.display.JFrameBuilder;
import org.somerville.swag.display.LandingPage;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LoggedIn implements CustomerState {

    private final Customer customer;
    private final DBSource dbSource = new SQLiteSource();

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public LoggedIn(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp(JPanel root, JFrame oldFrame, List<String> guestData) {
        showMessage(root, "Uh Oh! Can't SwagUp When SwaggedIn!", "SwagUp Swag-No", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void logIn(JPanel root, String email, String password) {
        showMessage(root, "Uh Oh! Can't SwagIn When SwaggedIn!", "SwagIn Swag-No", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void logOut(JPanel root) {
        customer.changeCustomerState(new Guest(customer));
        customer.refresh();

        showMessage(root, "SwaggedOut Success. D-money will miss you","Swagcess", JOptionPane.INFORMATION_MESSAGE);
        loggingService.logCustomerLoggedOut(customer.getCustomerId());
    }

    @Override
    public void addProductToBasket(JPanel root, Product product, int quantity) {
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

            customer.getCurrentOrder().add(new OrderLine(product, quantity));
            dbSource.updateProductStockLevel(product.getProductId(), productNewStockLevel);
            loggingService.logCustomerAddProductToBasket(customer.getCustomerId(), product.getProductId());
        }
    }

    @Override
    public void removeProductFromBasket(JPanel root, OrderLine orderLine) {
        Product selectedProduct = orderLine.getProduct();
        int selectedProductQuantity = orderLine.getQuantity();
        Order customerOrder = customer.getCurrentOrder();
        List<OrderLine> customerOrderLines = customerOrder.getOrderLines();

        customerOrderLines.remove(orderLine);
        selectedProduct.setStockLevel(selectedProduct.getStockLevel() + selectedProductQuantity);

        dbSource.updateProductStockLevel(selectedProduct.getProductId(), selectedProduct.getStockLevel());
        loggingService.logCustomerRemoveProductFromBasket(customer.getCustomerId(), orderLine.getProduct().getProductId());
    }

    @Override
    public void purchaseProducts(JFrame oldFrame, JPanel root, String txtCardNo, String txtCvv) {
        if (cardInvalid(txtCardNo, txtCvv)) {
            showMessage(root, "Incorrect Card Number \n-Format as 1234123412341234 \n-Format as 123",
                    "Card Number Error", JOptionPane.ERROR_MESSAGE);
        } else {
            showMessage(root, "Your Swag will be with you as soon as possible :)",
                    "Swag Success", JOptionPane.INFORMATION_MESSAGE);

            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();

            customer.clearBasket();
            loggingService.logCustomerCheckout(customer.getCustomerId(), customer.getCurrentOrder().getOrderId());
        }
    }

    private void showMessage(JPanel root, String message, String title, int type) {
        JOptionPane.showMessageDialog(root, message, title, type);
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    private boolean cardInvalid(String txtCardNo, String txtCvv) {
        return !txtCardNo.strip().matches("[0-9]{16}") || !txtCvv.strip().matches("[0-9]{3}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggedIn loggedIn = (LoggedIn) o;
        return Objects.equals(customer, loggedIn.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer);
    }
}
