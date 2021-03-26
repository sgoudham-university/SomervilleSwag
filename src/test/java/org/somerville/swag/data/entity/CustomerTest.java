package org.somerville.swag.data.entity;

import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entity.state.Guest;
import org.somerville.swag.data.entity.state.LoggedIn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerTest {

    @Test
    void customerShouldInstantiateWithLoggedInState() {
        String expectedEmail = "testEmail";
        String expectedPassword = "testPassword";
        Customer actualCustomer = new Customer(expectedEmail, expectedPassword, new Order());
        CustomerState expectedCustomerState = new LoggedIn(actualCustomer);

        CustomerState actualCustomerState = actualCustomer.getCustomerState();

        assertThat(actualCustomerState, is(expectedCustomerState));
    }

    @Test
    void customerShouldInstantiateWithGuestState() {
        Customer actualCustomer = new Customer();
        CustomerState expectedCustomerState = new Guest(actualCustomer);

        CustomerState actualCustomerState = actualCustomer.getCustomerState();

        assertThat(actualCustomerState, is(expectedCustomerState));
    }

    @Test
    void customerStateShouldNotBeNull() {
        Customer expectedCustomer = new Customer();
        assertNotNull(expectedCustomer.getCustomerState());
    }

    @Test
    void customerDetailsAreRefreshed() {
        int expectedCustomerId = 0;
        String expectedForename = null;
        String expectedSurname = null;
        String expectedEmail = null;
        String expectedPassword = null;
        String expectedAddressLine1 = null;
        String expectedAddressLine2 = null;
        String expectedCity = null;
        String expectedPostcode = null;
        String expectedPhoneNumber = null;

        Customer actualCustomer = createExpectedCustomer();
        actualCustomer.refresh();

        assertAll("Should Return Fully Populated Customer",
                () -> assertThat(actualCustomer.getCustomerId(), is(expectedCustomerId)),
                () -> assertThat(actualCustomer.getForename(), is(expectedForename)),
                () -> assertThat(actualCustomer.getSurname(), is(expectedSurname)),
                () -> assertThat(actualCustomer.getEmail(), is(expectedEmail)),
                () -> assertThat(actualCustomer.getPassword(), is(expectedPassword)),
                () -> assertThat(actualCustomer.getAddressLine1(), is(expectedAddressLine1)),
                () -> assertThat(actualCustomer.getAddressLine2(), is(expectedAddressLine2)),
                () -> assertThat(actualCustomer.getCity(), is(expectedCity)),
                () -> assertThat(actualCustomer.getPostcode(), is(expectedPostcode)),
                () -> assertThat(actualCustomer.getPhoneNumber(), is(expectedPhoneNumber))
        );
    }

    private Customer createExpectedCustomer() {
        return new Customer(
                new Order(),
                1,
                "testForename",
                "testSurname",
                "testEmail",
                "testPassword",
                "testAddressLine1",
                "testAddressLine2",
                "testCity",
                "testPostcode",
                "testPhoneNumber"
        );
    }

}