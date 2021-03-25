package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.CustomerState;
import org.somerville.swag.data.entity.OrderLine;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class LoggedIn implements CustomerState {

    private final Customer customer;

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public LoggedIn(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp(JPanel root, JFrame oldFrame, List<String> guestData) {
        showMessage(root, "Uh Oh! Can't SwagUp When SwaggedIn!", JOptionPane.ERROR_MESSAGE, "SwagUp Swag-No");
    }

    @Override
    public void logIn(JPanel root, String email, String password) {
        showMessage(root, "Uh Oh! Can't SwagIn When SwaggedIn!", JOptionPane.ERROR_MESSAGE, "SwagIn Swag-No");
    }

    @Override
    public void logOut(JPanel root) {
        customer.changeCustomerState(new Guest(customer));
        customer.refresh();

        showMessage(root, "SwaggedOut Success. D-money will miss you", JOptionPane.INFORMATION_MESSAGE, "Swagcess");
        loggingService.logCustomerLoggedOut(customer.getCustomerId());
    }

    @Override
    public void viewBasket() {

    }

    @Override
    public void addProductToBasket(Product product, int quantity) {
        customer.getCurrentOrder().add(new OrderLine(product, quantity));
        product.setStockLevel(product.getStockLevel() - quantity);

        // TODO: Write New Product Stock Level To Database

        loggingService.logCustomerAddProductToBasket(customer.getCustomerId(), product.getProductId());
    }

    @Override
    public void removeProductFromBasket(OrderLine orderLine) {
        customer.getCurrentOrder().getOrderLines().remove(orderLine);
        Product selectedProduct = orderLine.getProduct();
        int quantity = orderLine.getQuantity();
        selectedProduct.setStockLevel(selectedProduct.getStockLevel() - quantity);

        // TODO: Write New Product Stock Level to Database
    }

    @Override
    public void purchaseItems(JPanel root, String txtCardNo, String txtCvv) {

        if (!txtCardNo.strip().matches("[0-9]{16}") || !txtCvv.strip().matches("[0-9]{3}")) {
            showMessage(root, "Incorrect Card Number \n-Format as 1234123412341234 \n-Format as 123", JOptionPane.ERROR_MESSAGE, "Card Number Error");
        } else {
            showMessage(root, "Your Swag will be with you as soon as possible :)", JOptionPane.INFORMATION_MESSAGE, "Swag Success");
        }

        // TODO: Validate payment information

        // TODO: Show Popup of Order Confirmation

        // TODO: Clear customer basket in memory
    }

    private void showMessage(JPanel root, String message, int type, String title) {
        JOptionPane.showMessageDialog(root, message,
                title, type);
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
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
