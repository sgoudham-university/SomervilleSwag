package org.somerville.swag.data.entity.state;

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
        String expectedEmail = "testEmail";
        String expectedPassword = "testPassword";
        Customer actualCustomer = new Customer(expectedEmail, expectedPassword, new Order());

        CustomerState expectedCustomerState = new LoggedIn(actualCustomer);
        assertThat(actualCustomer.getCustomerState(), is(expectedCustomerState));
    }

    @Test
    void customerShouldInstantiateWithGuestState() {
        Customer actualCustomer = new Customer();
        CustomerState expectedCustomerState = new Guest(actualCustomer);
        assertThat(actualCustomer.getCustomerState(), is(expectedCustomerState));
    }

    @Test
    void customerStateShouldNotBeNull() {
        Customer expectedCustomer = new Customer();
        assertNotNull(expectedCustomer.getCustomerState());
    }
}