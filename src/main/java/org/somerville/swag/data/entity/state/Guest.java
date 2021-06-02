package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.*;
import org.somerville.swag.data.entity.util.Common;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;
import org.somerville.swag.data.source.DBSource;
import org.somerville.swag.data.source.SQLiteSource;
import org.somerville.swag.display.JFrameBuilder;
import org.somerville.swag.display.LandingPage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.somerville.swag.data.entity.util.Validation.isCustomerInfoInvalid;

public class Guest implements CustomerState {

    private final Customer customer;
    private final DBSource dbSource = new SQLiteSource();

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public Guest(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp(JPanel root, JFrame oldFrame, List<String> guestData) {
        String forename = guestData.get(0);
        String surname = guestData.get(1);
        String email = guestData.get(2);
        String password = guestData.get(3);
        String passwordConfirm = guestData.get(4);
        String addressLine1 = guestData.get(5);
        String addressLine2 = guestData.get(6);
        String city = guestData.get(7);
        String postcode = guestData.get(8);
        String phoneNumber = guestData.get(9);

        if (isCustomerInfoInvalid(forename, surname, email, password, passwordConfirm, addressLine1, city, postcode, phoneNumber)) {
            Common.showMessage(root, "SwagUp Error", "Uh Oh! Details Are Not Swag! Please Ensure The Following:\n" +
                    "Name: Forename & Surname must be 1-15 Swags Wide With No Numbers\n" +
                    "Email: You Should Know What an Email Looks Like :D\n" +
                    "Password: [4-15] Characters Please, No Special Characters Except Underscores\n" +
                    "Address: No Special Characters, Whitespace is Permitted\n" +
                    "Postcode: [6-7] Characters & Numbers Allowed, Whitespace Permitted\n" +
                    "Phone Number: 11 Numbers Only\n", JOptionPane.ERROR_MESSAGE);
        } else {
            if (dbSource.ifCustomerExists(email, password)) {
                Common.showMessage(root, "SwagIn Error", "Uh Oh! Customer Already Exists With Email: " + email, JOptionPane.ERROR_MESSAGE);
                loggingService.logDatabaseCustomerAlreadyExists();
            } else {
                List<String> customerData = new ArrayList<>() {{
                    add(forename);
                    add(surname);
                    add(email);
                    add(password);
                    add(addressLine1);
                    add(addressLine2);
                    add(city);
                    add(postcode);
                    add(phoneNumber);
                }};

                dbSource.insertCustomer(customerData);
                loggingService.logCustomerSignedUp(email);

                Common.showMessage(root, "Sign Up Swagsess", "Congrats! You've Created A Swag Account", JOptionPane.INFORMATION_MESSAGE);

                new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(oldFrame, customer).root, true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        }
    }

    @Override
    public void logIn(JPanel root, String email, String password) {
        dbSource.getCustomer(email, password, customer);

        if (customer.getEmail() != null) {
            customer.changeCustomerState(new LoggedIn(customer));
            Common.showMessage(root, "SwagIn Swagsess", "You've Swagged into your Swag Account", JOptionPane.INFORMATION_MESSAGE);
            loggingService.logCustomerLoggedIn(customer.getCustomerId());
        } else {
            Common.showMessage(root, "SwagIn Error", "Uh Oh! No Swag Customer Account Found!", JOptionPane.ERROR_MESSAGE);
            loggingService.logDatabaseCustomerNotFound(email);
        }
    }

    @Override
    public void logOut(JPanel root) {
        Common.showMessage(root, "SwagOut Swag-No", "Can't SwagOut If You Ain't Swagged In!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void addProductToBasket(JPanel root, Product product, int quantity) {
        if (quantity == 0) {
            Common.showMessage(root, "No Swag", "Your Quantity Of Swag Is Below The Minimum Swag Value",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            Common.addProductToBasket(root, customer, product, quantity);
        }
    }

    @Override
    public void removeProductFromBasket(JPanel root, OrderLine orderLine) {
        Common.removeProductFromBasket(customer, orderLine);
    }

    @Override
    public void purchaseProducts(JFrame oldFrame, JPanel root, String txtCardNo, String txtCvv) {
        Common.showMessage(root, "Check Out SwagNo", "Can't Checkout If You Ain't Swagged In!", JOptionPane.ERROR_MESSAGE);
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(customer, guest.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer);
    }
}
