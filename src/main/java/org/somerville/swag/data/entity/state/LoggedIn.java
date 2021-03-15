package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.CustomerState;

import java.util.Objects;

public class LoggedIn implements CustomerState {

    Customer customer;

    public LoggedIn(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp() {

    }

    @Override
    public void logIn() {

    }

    @Override
    public void logOut() {

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
