package org.somerville.swag.data.state;

import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entities.Customer;

import static org.junit.jupiter.api.Assertions.*;

class GuestStateTest {

    @Test
    void customerLoggedIn() {
        Customer c = new Customer("test","test");
        State s = new LoggedInState(c);
        assertEquals(c.getState(), s.customer.getState());
    }

    @Test
    void testCustomerLoggedIn() {
        Customer c = new Customer();
        State s = new LoggedInState(c);
        assertEquals(c.getState(), s.customer.getState());
    }
}