package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Order;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.service.LoggingService;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

class DBMapperTest {

    @Mock
    LoggingService loggingService;

    @Mock
    ResultSet resultSetMock;

    DBMapper dbMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dbMapper = new DBMapper();
        dbMapper.setLoggingService(loggingService);
    }

    @Test
    void successfullyMapResultSetToCustomer() throws SQLException {
        Customer expectedCustomer = createTestCustomer();
        Customer actualCustomer = new Customer();

        when(resultSetMock.isBeforeFirst()).thenReturn(true);
        when(resultSetMock.next())
                .thenReturn(true)
                .thenReturn(false);
        when(resultSetMock.getInt("CustomerId")).thenReturn(expectedCustomer.getCustomerId());
        when(resultSetMock.getString("Forename")).thenReturn(expectedCustomer.getForename());
        when(resultSetMock.getString("Surname")).thenReturn(expectedCustomer.getSurname());
        when(resultSetMock.getString("Email")).thenReturn(expectedCustomer.getEmail());
        when(resultSetMock.getString("Password")).thenReturn(expectedCustomer.getPassword());
        when(resultSetMock.getString("AddressLine1")).thenReturn(expectedCustomer.getAddressLine1());
        when(resultSetMock.getString("AddressLine2")).thenReturn(expectedCustomer.getAddressLine2());
        when(resultSetMock.getString("City")).thenReturn(expectedCustomer.getCity());
        when(resultSetMock.getString("Postcode")).thenReturn(expectedCustomer.getPostcode());
        when(resultSetMock.getString("PhoneNumber")).thenReturn(expectedCustomer.getPhoneNumber());

        dbMapper.mapToCustomer(resultSetMock, actualCustomer);

        assertThat(actualCustomer, is(expectedCustomer));
        verify(loggingService, times(1)).logDatabaseCustomerMapSuccess(expectedCustomer.getCustomerId());
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToMapResultSetToCustomer() throws SQLException {
        String expectedExceptionMessage = "Failure!";
        Exception expectedException = new SQLException(expectedExceptionMessage);
        Customer actualCustomer = new Customer();

        when(resultSetMock.isBeforeFirst()).thenReturn(true);
        when(resultSetMock.next()).thenThrow(expectedException);

        dbMapper.mapToCustomer(resultSetMock, actualCustomer);

        verify(loggingService, times(1)).logDatabaseCustomerMapFailure(expectedExceptionMessage);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void successfullyMapResultSetToListOfProducts() throws SQLException {
        List<Product> expectedAllProducts = createTestListOfProducts();
        List<Product> actualAllProducts = new ArrayList<>();
        Product expectedFirstProduct = expectedAllProducts.get(0);
        Product expectedSecondProduct = expectedAllProducts.get(1);

        when(resultSetMock.isBeforeFirst()).thenReturn(true);
        when(resultSetMock.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(resultSetMock.getInt("ProductId"))
                .thenReturn(expectedFirstProduct.getProductId())
                .thenReturn(expectedSecondProduct.getProductId());
        when(resultSetMock.getString("Name"))
                .thenReturn(expectedFirstProduct.getName())
                .thenReturn(expectedSecondProduct.getName());
        when(resultSetMock.getString("Description"))
                .thenReturn(expectedFirstProduct.getDescription())
                .thenReturn(expectedSecondProduct.getDescription());
        when(resultSetMock.getBigDecimal("Price"))
                .thenReturn(expectedFirstProduct.getPrice())
                .thenReturn(expectedSecondProduct.getPrice());
        when(resultSetMock.getInt("StockLevel"))
                .thenReturn(expectedFirstProduct.getStockLevel())
                .thenReturn(expectedSecondProduct.getStockLevel());
        when(resultSetMock.getString("ImagePath"))
                .thenReturn(expectedFirstProduct.getImagePath())
                .thenReturn(expectedSecondProduct.getImagePath());

        dbMapper.mapToProducts(resultSetMock, actualAllProducts);

        assertThat(actualAllProducts, is(expectedAllProducts));
        verify(loggingService, times(1)).logDatabaseAllProductsMapSuccess();
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToFindAnyProductsInStockFromDatabase() throws SQLException {
        List<Product> actualAllProducts = new ArrayList<>();

        when(resultSetMock.isBeforeFirst()).thenReturn(false);

        dbMapper.mapToProducts(resultSetMock, actualAllProducts);

        verify(loggingService, times(1)).logDatabaseNoProductsInStock();
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToMapResultSetToListOfProducts() throws SQLException {
        String expectedExceptionMessage = "Failure!";
        Exception expectedException = new SQLException(expectedExceptionMessage);
        List<Product> actualAllProducts = new ArrayList<>();

        when(resultSetMock.isBeforeFirst()).thenReturn(true);
        when(resultSetMock.next()).thenThrow(expectedException);

        dbMapper.mapToProducts(resultSetMock, actualAllProducts);

        verify(loggingService, times(1)).logDatabaseAllProductsMapFailure(expectedExceptionMessage);
        verifyNoMoreInteractions(loggingService);
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

    private List<Product> createTestListOfProducts() {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(new Product(
                1,
                "testName1",
                "testDescription1",
                new BigDecimal(1),
                1,
                "testImagePath1"
        ));
        allProducts.add(new Product(
                2,
                "testName2",
                "testDescription2",
                new BigDecimal(2),
                1,
                "testImagePath2"
        ));
        return allProducts;
    }
}