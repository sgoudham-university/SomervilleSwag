package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.service.LoggingService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class DBMapperTest {

    @Mock
    LoggingService loggingService;

    @Mock
    ResultSet resultSet;

    DBMapper dbMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dbMapper = new DBMapper();
        dbMapper.setLoggingService(loggingService);
    }

    @Test
    void successfullyMapResultSetToCustomer() throws SQLException {
        int expectedCustomerId = 0;
        String expectedForename = "testForename";
        String expectedSurname = "testSurname";
        String expectedEmail = "testEmail";
        String expectedPassword = "testPassword";
        String expectedAddressLine1 = "testAddressLine1";
        String expectedAddressLine2 = "testAddressLine2";
        String expectedCity = "testCity";
        String expectedPostcode = "expectedPostcode";
        String expectedPhoneNumber = "expectedPhoneNumber";
        Customer actualCustomer = new Customer();

        when(resultSet.isBeforeFirst()).thenReturn(true);
        when(resultSet.next())
                .thenReturn(true)
                .thenReturn(false);
        when(resultSet.getInt("CustomerId")).thenReturn(expectedCustomerId);
        when(resultSet.getString("Forename")).thenReturn(expectedForename);
        when(resultSet.getString("Surname")).thenReturn(expectedSurname);
        when(resultSet.getString("Email")).thenReturn(expectedEmail);
        when(resultSet.getString("Password")).thenReturn(expectedPassword);
        when(resultSet.getString("AddressLine1")).thenReturn(expectedAddressLine1);
        when(resultSet.getString("AddressLine2")).thenReturn(expectedAddressLine2);
        when(resultSet.getString("City")).thenReturn(expectedCity);
        when(resultSet.getString("Postcode")).thenReturn(expectedPostcode);
        when(resultSet.getString("PhoneNumber")).thenReturn(expectedPhoneNumber);

        dbMapper.mapToCustomer(resultSet, actualCustomer);

        assertThat(actualCustomer.getCustomerId(), is(expectedCustomerId));
        assertThat(actualCustomer.getForename(), is(expectedForename));
        assertThat(actualCustomer.getSurname(), is(expectedSurname));
        assertThat(actualCustomer.getEmail(), is(expectedEmail));
        assertThat(actualCustomer.getPassword(), is(expectedPassword));
        assertThat(actualCustomer.getAddressLine1(), is(expectedAddressLine1));
        assertThat(actualCustomer.getAddressLine2(), is(expectedAddressLine2));
        assertThat(actualCustomer.getCity(), is(expectedCity));
        assertThat(actualCustomer.getPostcode(), is(expectedPostcode));
        assertThat(actualCustomer.getPhoneNumber(), is(expectedPhoneNumber));
        verify(loggingService, times(1)).logDatabaseCustomerMapSuccess(expectedCustomerId);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToMapResultSetToCustomer() throws SQLException {
        ResultSet expectedResultSet = spy(ResultSet.class);

        when(expectedResultSet.isBeforeFirst()).thenReturn(false);

        dbMapper.mapToCustomer(expectedResultSet, new Customer());
    }

    @Test
    void successfullyMapResultSetToListOfProducts() {

    }

    @Test
    void failToMapResultSetToListOfProducts() {

    }
}