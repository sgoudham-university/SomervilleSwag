package org.somerville.swag.data.state;

import org.somerville.swag.data.entities.Customer;

public class LoggedInState extends State{

    public LoggedInState(Customer customer) {
        super(customer);
    }

    @Override
    public void customerLoggedIn(Customer customer) {
        if(!customer.getUsername().equals("")) {
            customer.setState(new LoggedInState(customer));
        }
    }

    @Override
    public void customerGuest(Customer customer) {

    }


}
