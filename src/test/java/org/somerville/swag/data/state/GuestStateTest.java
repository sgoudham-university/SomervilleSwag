package org.somerville.swag.data.state;

import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.CustomerState;
import org.somerville.swag.data.entity.Order;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;

class GuestStateTest {

    @Test
    void customerShouldInstantiateWithLoggedInState() {
        Customer expectedCustomer = new Customer("test", "test", new Order());
        CustomerState actualCustomerState = new LoggedIn(expectedCustomer);
        assertThat(actualCustomerState, is(expectedCustomer.getCustomerState()));
    }

    @Test
    void customerShouldInstantiateWithGuestState() {
        Customer expectedCustomer = new Customer();
        CustomerState actualCustomerState = new Guest(expectedCustomer);
        assertThat(actualCustomerState, is(expectedCustomer.getCustomerState()));
    }

    @Test
    void customerStateShouldNotBeNull() {
        Customer expectedCustomer = new Customer();
        assertNotNull(expectedCustomer.getCustomerState());
    }
}