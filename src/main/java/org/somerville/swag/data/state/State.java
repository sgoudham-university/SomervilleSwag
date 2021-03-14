package org.somerville.swag.data.state;

import org.somerville.swag.data.entities.Customer;

public abstract class State {
    Customer customer;

    State(Customer customer){
        this.customer = customer;
    }

    public abstract void customerLoggedIn(Customer customer);
    public abstract void customerGuest(Customer customer);
}
