package org.somerville.swag.data.state;

import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entities.Customer;

import static org.junit.jupiter.api.Assertions.*;

class GuestStateTest {
    /**
     * unsure if assert stmt is correct.
     */

    @Test
    void test_customerLoggedInState() {
        Customer c = new Customer("test","test");
        State s = new LoggedInState(c);
        assertEquals(c.getState(), s.customer.getState());
    }

    @Test
    void test_CustomerGuestState() {
        Customer c = new Customer();
        State s = new LoggedInState(c);
        assertEquals(c.getState(), s.customer.getState());
    }
}