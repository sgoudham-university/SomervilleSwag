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
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Guest implements CustomerState {

    private final Customer customer;
    private final Common commonCode = new Common();
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

        if (invalidInformation(forename, surname, email, password, passwordConfirm, addressLine1, city, postcode, phoneNumber)) {
            showMessage(root, "SwagIn Error", "Uh Oh! Details Are Not Swag! Please Ensure The Following:\n" +
                    "Name: Forename & Surname must be 1-15 Swags Wide With No Numbers\n" +
                    "Email: You Should Know What an Email Looks Like :D\n" +
                    "Password: [4-15] Characters Please, No Special Characters Except Underscores\n" +
                    "Address: No Special Characters, Whitespace is Permitted\n" +
                    "Postcode: [6-7] Characters & Numbers Allowed, Whitespace Permitted\n" +
                    "Phone Number: 11 Numbers Only\n" ,JOptionPane.ERROR_MESSAGE);
        } else {
            if (dbSource.ifCustomerExists(email, password)) {
                showMessage(root, "SwagIn Error", "Uh Oh! Customer Already Exists With Email: " + email, JOptionPane.ERROR_MESSAGE);
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
                showMessage(root, "Sign Up Swagsess", "Congrats! You've Created A Swag Account", JOptionPane.INFORMATION_MESSAGE);
                loggingService.logCustomerSignedUp(email);

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
            showMessage(root, "SwagIn Swagsess", "You've Swagged into your Swag Account", JOptionPane.INFORMATION_MESSAGE);
            loggingService.logCustomerLoggedIn(customer.getCustomerId());
        } else {
            showMessage(root, "SwagIn Error", "Uh Oh! No Swag Customer Account Found!", JOptionPane.ERROR_MESSAGE);
            loggingService.logDatabaseCustomerNotFound(email);
        }
    }

    @Override
    public void logOut(JPanel root) {
        showMessage(root, "SwagOut Swag-No", "Can't SwagOut If You Ain't Swagged In!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void addProductToBasket(JPanel root, Product product, int quantity) {
        if (quantity == 0) {
            showMessage(root, "No Swag", "Your Quantity Of Swag Is Below The Minimum Swag Value",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            commonCode.addProductToBasket(root, customer, product, quantity);
        }
    }

    @Override
    public void removeProductFromBasket(JPanel root, OrderLine orderLine) {
        commonCode.removeProductFromBasket(customer, orderLine);
    }

    @Override
    public void purchaseProducts(JFrame oldFrame, JPanel root, String txtCardNo, String txtCvv) {
        JOptionPane.showMessageDialog(root, "Can't Checkout If You Ain't Swagged In!",
                "Check Out SwagNo", JOptionPane.ERROR_MESSAGE);
    }

    private boolean invalidInformation(String forename, String surname, String email, String password,
                                       String passwordConfirm, String addressLineOne, String city, String postcode,
                                       String phoneNumber) {
        return !forename.strip().matches("[a-zA-Z]{1,15}") || //only characters, min length 1 - max length 15
                !surname.strip().matches("[a-zA-Z]{1,15}") || //only characters, min length 1 - max length 15
                !email.strip().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$") || //Simple email expression. Doesn't allow numbers in the domain name and doesn't allow for top level domains that are less than 2 or more than 3 letters (which is fine until they allow more). Doesn't handle multiple &quot;.&quot; in the domain (joe@abc.co.uk).
                !password.matches("^[a-zA-Z]\\w{3,14}$") || //The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be use
                !passwordConfirm.matches("^[a-zA-Z]\\w{3,14}$") || //The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be used
                !addressLineOne.matches("^[a-zA-Z0-9_ ]*$") || //no special characters, whisepace premitted
                !city.matches("^[a-zA-Z_ ]*$") || //Only characters, whitespace permitted, will accept empty string
                !postcode.matches("^[a-zA-Z0-9_ ]{6,7}$") || //characters and numbers, 6-7 characters, whitespace permitted
                !phoneNumber.matches("[0-9]{11}") || //only numbers,, 11 characters
                !password.equals(passwordConfirm); //both password fields must match
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    private void showMessage(JPanel root, String title, String message, int type) {
        JOptionPane.showMessageDialog(root, message, title, type);
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
