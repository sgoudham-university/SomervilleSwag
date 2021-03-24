package org.somerville.swag.data.entity.state;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.CustomerState;
import org.somerville.swag.data.source.DBSource;
import org.somerville.swag.data.source.SQLiteSource;

import java.util.Objects;

public class Guest implements CustomerState {

    private final Customer customer;
    private final DBSource dbSource = new SQLiteSource();

    public Guest(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void signUp() {

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
