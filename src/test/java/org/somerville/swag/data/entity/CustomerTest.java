package org.somerville.swag.data.entity;

import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entity.state.Guest;
import org.somerville.swag.data.entity.state.LoggedIn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void successfullyInstantiateCustomerWithLoggedInState() {
        String expectedEmail = "testEmail";
        String expectedPassword = "testPassword";
        Customer actualCustomer = new Customer(expectedEmail, expectedPassword, new Order());
        CustomerState expectedCustomerState = new LoggedIn(actualCustomer);

        CustomerState actualCustomerState = actualCustomer.getCustomerState();

        assertThat(actualCustomerState, is(expectedCustomerState));
    }

    @Test
    void successfullyInstantiateCustomerWithGuestState() {
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
    void successfullyClearCustomerBasket() {
        String expectedEmail = "testEmail";
        String expectedPassword = "testPassword";
        Order expectedOrder = new Order();
        expectedOrder.setOrderLines(createTestListOfProducts());

        Customer actualCustomer = new Customer(expectedEmail, expectedPassword, expectedOrder);

        actualCustomer.clearBasket();

        assertThat(actualCustomer.getCurrentOrder(), is(new Order()));
    }

    @Test
    void successfullyRefreshCustomerDetails() {
        Customer expectedCustomer = new Customer();
        Customer actualCustomer = createTestCustomer();

        actualCustomer.refresh();

        assertThat(actualCustomer, is(expectedCustomer));
    }

    private Customer createTestCustomer() {
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

    private List<OrderLine> createTestListOfProducts() {
        List<OrderLine> allProducts = new ArrayList<>();
        Product testFirstProduct = new Product(1, "testName1", "testDescription1", new BigDecimal(1), 1, "testImagePath1");
        Product testSecondProduct = new Product(2, "testName2", "testDescription2", new BigDecimal(2), 1, "testImagePath2");
        allProducts.add(new OrderLine(testFirstProduct, 1));
        allProducts.add(new OrderLine(testSecondProduct, 1));
        return allProducts;
    }
}