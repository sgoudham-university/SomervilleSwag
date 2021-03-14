package org.somerville.swag.data.state;

import org.somerville.swag.data.entities.Customer;

public class GuestState extends State {
    public GuestState(Customer customer) {
        super(customer);
    }

    @Override
    public void customerLoggedIn(Customer customer) {

    }

    @Override
    public void customerGuest(Customer customer) {
        if(customer.getUsername().equals("")){               //maybe null?
            customer.setState(new GuestState(customer));
        }
    }


}
