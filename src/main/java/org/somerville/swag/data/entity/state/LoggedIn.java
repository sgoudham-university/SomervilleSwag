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
    public void signUp(JPanel root, List<String> guestData) {
        JOptionPane.showMessageDialog(root, "Uh Oh! Can't Sign Up When Logged In!",
                "Sign Up Swag-no", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void logIn(JPanel root, String email, String password) {
        JOptionPane.showMessageDialog(root, "Uh Oh! Can't Log In When Logged In!",
                "Log In Swag-no", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void logOut(JPanel root) {
        customer.changeCustomerState(new Guest(customer));

        // TODO implement refresh method

        JOptionPane.showMessageDialog(root, "Logged out Success. D-money will miss you",
                "Logged out", JOptionPane.INFORMATION_MESSAGE);
        loggingService.logCustomerLoggedOut(customer.getCustomerId());
    }

    @Override
    public void viewBasket() {

    }

    @Override
    public void addProductToBasket(Product product, int quantity) {

    }

    @Override
    public void removeProductFromBasket(OrderLine orderLine) {

    }

    @Override
    public void purchaseItems(JPanel root) {

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
