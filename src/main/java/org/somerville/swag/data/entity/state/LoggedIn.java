package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.CustomerState;

import javax.swing.*;
import java.util.Objects;

public class LoggedIn implements CustomerState {

    private Customer customer;

    public LoggedIn(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp(JPanel root, String... guestData) {
        //TODO throw error mesage
    }

    @Override
    public void logIn(JPanel root, String email, String password) {
        JOptionPane.showMessageDialog(root, "Cant Log in when you're already logged in",
                "Log In Swag-no", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void logOut(JPanel root) {
        //customer = null
        // TODO implement refresh method && change state.
        customer.changeCustomerState(new Guest(customer));
        JOptionPane.showMessageDialog(root, "Logged out Success. D-money will miss you",
                "Logged out", JOptionPane.INFORMATION_MESSAGE);
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
