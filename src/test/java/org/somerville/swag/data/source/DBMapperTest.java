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
        Customer expectedCustomer = createExpectedCustomer();
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

        assertAll("Should Return Fully Populated Customer",
                () -> assertThat(actualCustomer.getCustomerId(), is(expectedCustomer.getCustomerId())),
                () -> assertThat(actualCustomer.getForename(), is(expectedCustomer.getForename())),
                () -> assertThat(actualCustomer.getSurname(), is(expectedCustomer.getSurname())),
                () -> assertThat(actualCustomer.getEmail(), is(expectedCustomer.getEmail())),
                () -> assertThat(actualCustomer.getPassword(), is(expectedCustomer.getPassword())),
                () -> assertThat(actualCustomer.getAddressLine1(), is(expectedCustomer.getAddressLine1())),
                () -> assertThat(actualCustomer.getAddressLine2(), is(expectedCustomer.getAddressLine2())),
                () -> assertThat(actualCustomer.getCity(), is(expectedCustomer.getCity())),
                () -> assertThat(actualCustomer.getPostcode(), is(expectedCustomer.getPostcode())),
                () -> assertThat(actualCustomer.getPhoneNumber(), is(expectedCustomer.getPhoneNumber()))
        );
        verify(loggingService, times(1)).logDatabaseCustomerMapSuccess(expectedCustomer.getCustomerId());
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToFindCustomerAccountInDatabase() throws SQLException {
        Customer actualCustomer = new Customer();

        when(resultSetMock.isBeforeFirst()).thenReturn(false);

        dbMapper.mapToCustomer(resultSetMock, actualCustomer);

        verify(loggingService, times(1)).logDatabaseCustomerNotFound();
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
        List<Product> expectedAllProducts = createExpectedListOfProducts();
        Product expectedFirstProduct = expectedAllProducts.get(0);
        Product expectedSecondProduct = expectedAllProducts.get(1);
        List<Product> actualAllProducts = new ArrayList<>();

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

        Product actualFirstProduct = actualAllProducts.get(0);
        Product actualSecondProduct = actualAllProducts.get(1);
        assertAll("Should Return First Fully Populated Product",
                () -> assertThat(actualFirstProduct.getProductId(), is(expectedFirstProduct.getProductId())),
                () -> assertThat(actualFirstProduct.getName(), is(expectedFirstProduct.getName())),
                () -> assertThat(actualFirstProduct.getDescription(), is(expectedFirstProduct.getDescription())),
                () -> assertThat(actualFirstProduct.getPrice(), is(expectedFirstProduct.getPrice())),
                () -> assertThat(actualFirstProduct.getStockLevel(), is(expectedFirstProduct.getStockLevel())),
                () -> assertThat(actualFirstProduct.getImagePath(), is(expectedFirstProduct.getImagePath()))
        );
        assertAll("Should Return Second Fully Populated Product",
                () -> assertThat(actualSecondProduct.getProductId(), is(expectedSecondProduct.getProductId())),
                () -> assertThat(actualSecondProduct.getName(), is(expectedSecondProduct.getName())),
                () -> assertThat(actualSecondProduct.getDescription(), is(expectedSecondProduct.getDescription())),
                () -> assertThat(actualSecondProduct.getPrice(), is(expectedSecondProduct.getPrice())),
                () -> assertThat(actualSecondProduct.getStockLevel(), is(expectedSecondProduct.getStockLevel())),
                () -> assertThat(actualSecondProduct.getImagePath(), is(expectedSecondProduct.getImagePath()))
        );
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

    private List<Product> createExpectedListOfProducts() {
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