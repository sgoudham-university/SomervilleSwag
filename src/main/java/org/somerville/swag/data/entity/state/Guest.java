package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.CustomerState;
import org.somerville.swag.data.source.DBSource;
import org.somerville.swag.data.source.SQLiteSource;

import java.util.Objects;

public class Guest implements CustomerState {

    Customer customer;

    public Guest(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp() {
        DBSource dbSource = new SQLiteSource();

        // Check Customer doesn't already exist
        Customer customer = dbSource.getCustomer("", "");
        if (customer == null) {
            // throw up error that account already exists
        }

        // Call DBExecuteInsertCustomer (Pass in Customer Object)
        dbSource.insertCustomer(customer);

        // Change state to LoggedIn
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
        Guest guest = (Guest) o;
        return Objects.equals(customer, guest.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer);
    }
}
