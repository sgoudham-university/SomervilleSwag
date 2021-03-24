package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.CustomerState;
import org.somerville.swag.data.source.DBSource;
import org.somerville.swag.data.source.SQLiteSource;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;

public class Guest implements CustomerState {

    private final Customer customer;
    private final DBSource dbSource = new SQLiteSource();

    public Guest(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp(JPanel root, String[] guestData) {

        String foreName = guestData[0];
        String surName = guestData[1];
        String email = guestData[2];
        String password = guestData[3];
        String passwordConfirm = guestData[4];
        String addressLineOne = guestData[5];
        String addressLineTwo = guestData[6];
        String city = guestData[7];
        String postcode = guestData[8];
        String phoneNo = guestData[9];

        if(!foreName.strip().matches("[a-zA-Z]{1,15}") || //only characters, min length 1 - max length 15
                !surName.strip().matches("[a-zA-Z]{1,15}") || //only characters, min length 1 - max length 15
                !email.strip().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$") || //Simple email expression. Doesn't allow numbers in the domain name and doesn't allow for top level domains that are less than 2 or more than 3 letters (which is fine until they allow more). Doesn't handle multiple &quot;.&quot; in the domain (joe@abc.co.uk).
                !password.matches("^[a-zA-Z]\\w{3,14}$") || //The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be use
                !passwordConfirm.matches("^[a-zA-Z]\\w{3,14}$") || //The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be used
                !addressLineOne.matches("^[a-zA-Z0-9_ ]*$") || //no special characters, whisepace premitted
                !city.matches("^[a-zA-Z_ ]*$") || //Only characters, whitespace permitted, will accept empty string
                !postcode.matches("^[a-zA-Z0-9_ ]{6,7}$") || //characters and numbers, 6-7 characters, whitespace permitted
                !phoneNo.matches("[0-9]{11}") || //only numbers,, 11 characters
                !password.equals(passwordConfirm) //both password fields must match
        )
        {
            JOptionPane.showMessageDialog(root, "Something went wrong and the code is so bad I dont know what",
                    "Sign In error", JOptionPane.ERROR_MESSAGE);

        } else {
            dbSource.getCustomer(email, password, customer);
            dbSource.insertCustomer(Arrays.asList(guestData));

            JOptionPane.showMessageDialog(root, "You've created a Swag Account",
                    "Sign Up Swagsess", JOptionPane.INFORMATION_MESSAGE);
        }
        // TODO: Check if customer already has an account (check email)

        // TODO: Throw JOptionPane Error if they already have an account

        // TODO: Call DBExecuteInsertCustomer (Pass in Customer Object)
        // dbSource.insertCustomer(customer);

    }

    @Override
    public void logIn() {
        dbSource.getCustomer("sgoudham@gmail.com", "testPassword", customer);

        if (customer.getEmail() != null) {
            customer.changeCustomerState(new LoggedIn(customer));
            // TODO: Log customer logged in success
        } else {
            // TODO: Throw up error that account not found
            // TODO: Log account not found
        }
    }

    @Override
    public void logOut() {
        // TODO: Display error popup that you aren't logged in
    }

    @Override
    public void viewBasket() {

    }

    @Override
    public void addProductToBasket() {

    }

    @Override
    public void removeProductFromBasket() {

    }

    @Override
    public void purchaseItems() {
        // TODO: Display error that you need to be logged in to checkout
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
