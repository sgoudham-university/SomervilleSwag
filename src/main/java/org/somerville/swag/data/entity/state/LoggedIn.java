package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.*;
import org.somerville.swag.data.entity.util.Common;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;
import org.somerville.swag.display.JFrameBuilder;
import org.somerville.swag.display.LandingPage;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

import static org.somerville.swag.data.entity.util.Validation.isCardInvalid;

public class LoggedIn implements CustomerState {

    private final Customer customer;

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public LoggedIn(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp(JPanel root, JFrame oldFrame, List<String> guestData) {
        Common.showMessage(root, "SwagUp Swag-No", "Uh Oh! Can't SwagUp When SwaggedIn!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void logIn(JPanel root, String email, String password) {
        Common.showMessage(root, "SwagIn Swag-No", "Uh Oh! Can't SwagIn When SwaggedIn!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void logOut(JPanel root) {
        customer.changeCustomerState(new Guest(customer));
        customer.refresh();

        Common.showMessage(root, "Swagcess", "SwaggedOut Success. D-money will miss you", JOptionPane.INFORMATION_MESSAGE);
        loggingService.logCustomerLoggedOut(customer.getCustomerId());
    }

    @Override
    public void addProductToBasket(JPanel root, Product product, int quantity) {
        if (quantity == 0) {
            Common.showMessage(root, "No Swag", "Your Quantity Of Swag Is Below The Minimum Swag Value",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            Common.addProductToBasket(root, customer, product, quantity);
            loggingService.logCustomerAddProductToBasket(customer.getCustomerId(), product.getProductId());
        }
    }

    @Override
    public void removeProductFromBasket(JPanel root, OrderLine orderLine) {
        Common.removeProductFromBasket(customer, orderLine);
        loggingService.logCustomerRemoveProductFromBasket(customer.getCustomerId(), orderLine.getProduct().getProductId());
    }

    @Override
    public void purchaseProducts(JFrame oldFrame, JPanel root, String txtCardNo, String txtCvv) {
        if (isCardInvalid(txtCardNo, txtCvv)) {
            Common.showMessage(root, "Money Not Swag", "Incorrect Swag Number \n- Format as 1234123412341234 \n- Format as 123", JOptionPane.ERROR_MESSAGE);
        } else {
            Order customerOrder = customer.getCurrentOrder();
            String customerOrderReceipt = customerOrder.getReceipt(customer);

            Common.showMessage(root, "Swag Success", customerOrderReceipt, JOptionPane.INFORMATION_MESSAGE);

            customer.clearBasket();
            loggingService.logCustomerCheckout(customer.getCustomerId(), customerOrder.getOrderId());

            new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
            SwingUtilities.getWindowAncestor(root).dispose();
        }
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
