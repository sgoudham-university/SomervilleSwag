package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.exception.SQLStatementException;
import org.somerville.swag.data.service.LoggingService;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SQLiteSourceTest {

    @Mock
    LoggingService loggingServiceMock;

    @Mock
    DBMapper sqLiteMapperMock;

    @Mock
    ResultSet resultSetMock;

    DBExecute sqLiteExecute;

    SQLiteSource sqLiteSource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sqLiteSource = new SQLiteSource();
        sqLiteExecute = spy(new SQLiteExecute(createSQLiteConnection()));
        sqLiteExecute.setLoggingService(loggingServiceMock);
        sqLiteSource.setLoggingService(loggingServiceMock);
        sqLiteSource.setDbExecute(sqLiteExecute);
        sqLiteSource.setDbMapper(sqLiteMapperMock);
    }

    @Test
    void successfullyGetCustomerFromDatabase() {
        String expectedDatabaseUrl = getExpectedDatabaseUrl();
        String expectedTestEmail = "testEmail";
        String expectedTestPassword = "testPassword";
        String expectedGetCustomerQuery = getCustomerQuery(expectedTestEmail, expectedTestPassword);
        Customer expectedCustomer = new Customer();

        doNothing().when(sqLiteMapperMock).mapToCustomer(resultSetMock, expectedCustomer);

        Customer actualCustomer = sqLiteSource.getCustomer(expectedTestEmail, expectedTestPassword, expectedCustomer);

        assertSame(actualCustomer, expectedCustomer);
        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verify(loggingServiceMock, times(1)).logDatabaseSelectSuccess(expectedGetCustomerQuery);
        verify(loggingServiceMock, times(1)).logDatabaseGetCustomerSuccess(expectedCustomer.getCustomerId());
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void failToGetCustomerFromDatabase() throws SQLStatementException {
        String expectedTestEmail = "testEmail";
        String expectedTestPassword = "testPassword";
        String expectedGetCustomerQuery = getCustomerQuery(expectedTestEmail, expectedTestPassword);
        Customer expectedCustomer = new Customer();

        String expectedExceptionMessage = "Failure!";
        SQLStatementException expectedException = new SQLStatementException(expectedExceptionMessage, new SQLException());

        doThrow(expectedException).when(sqLiteExecute).executeSelect(expectedGetCustomerQuery);

        sqLiteSource.getCustomer(expectedTestEmail, expectedTestPassword, expectedCustomer);

        verify(loggingServiceMock, times(1)).logDatabaseGetCustomerFailure(expectedGetCustomerQuery, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void successfullyInsertCustomerIntoDatabase() throws SQLStatementException {
        List<String> guestData = getGuestData();
        String expectedInsertCustomerStatement = insertCustomerStatement();

        doNothing().when(sqLiteExecute).executeUpdate(expectedInsertCustomerStatement);

        sqLiteSource.insertCustomer(guestData);

        verify(loggingServiceMock, times(1)).logDatabaseInsertCustomerSuccess();
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void failToInsertCustomerIntoDatabase() throws SQLStatementException {
        List<String> guestData = getGuestData();
        String expectedInsertCustomerStatement = insertCustomerStatement();

        String expectedExceptionMessage = "Failure!";
        SQLStatementException expectedException = new SQLStatementException(expectedExceptionMessage, new SQLException());

        doThrow(expectedException).when(sqLiteExecute).executeUpdate(expectedInsertCustomerStatement);

        sqLiteSource.insertCustomer(guestData);

        verify(loggingServiceMock, times(1)).logDatabaseInsertCustomerFailure(expectedInsertCustomerStatement, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void successfullyRetrieveAllProducts() {
        List<Product> expectedAllProducts = getAllExpectedProducts();
        String expectedDatabaseUrl = getExpectedDatabaseUrl();
        String expectedGetAllProductsQuery = "SELECT * FROM Product WHERE StockLevel >= 1;";

        DBMapper sqLiteMapper = new DBMapper();
        sqLiteMapper.setLoggingService(loggingServiceMock);
        sqLiteSource.setDbMapper(sqLiteMapper);

        List<Product> actualAllProducts = sqLiteSource.getAllProductsInStock();

        assertArrayEquals(actualAllProducts.toArray(), expectedAllProducts.toArray());
        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verify(loggingServiceMock, times(1)).logDatabaseSelectSuccess(expectedGetAllProductsQuery);
        verify(loggingServiceMock, times(1)).logDatabaseAllProductsMapSuccess();
        verify(loggingServiceMock, times(1)).logDatabaseGetAllProductsInStockSuccess();
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void failToRetrieveAllProducts() throws SQLStatementException {
        String expectedGetAllProductsQuery = "SELECT * FROM Product WHERE StockLevel >= 1;";

        String expectedExceptionMessage = "Failure!";
        SQLStatementException expectedException = new SQLStatementException(expectedExceptionMessage, new SQLException());

        doThrow(expectedException).when(sqLiteExecute).executeSelect(expectedGetAllProductsQuery);

        sqLiteSource.getAllProductsInStock();

        verify(loggingServiceMock, times(1)).logDatabaseGetAllProductsInStockFailure(expectedGetAllProductsQuery, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void successfullyFindCustomerInDatabase() {
        String expectedTestEmail = "testEmail";
        String expectedTestPassword = "testPassword";

        boolean actualIfCustomerExists = sqLiteSource.ifCustomerExists(expectedTestEmail, expectedTestPassword);

        assertTrue(actualIfCustomerExists);
    }

    @Test
    void failToFindCustomerInDatabase() {
        String expectedTestEmail = "testInvalidEmail";
        String expectedTestPassword = "testInvalidPassword";

        boolean actualIfCustomerExists = sqLiteSource.ifCustomerExists(expectedTestEmail, expectedTestPassword);

        assertFalse(actualIfCustomerExists);
    }

    @Test
    void successfullyUpdateProductStockLevelInDatabase() throws SQLStatementException {
        int expectedStockLevel = 1;
        int expectedProductId = 1;
        String expectedUpdateProductStockLevelStatement = updateProductStockLevelStatement(expectedProductId, expectedStockLevel);

        doNothing().when(sqLiteExecute).executeUpdate(expectedUpdateProductStockLevelStatement);

        sqLiteSource.updateProductStockLevel(expectedProductId, expectedStockLevel);

        verify(loggingServiceMock, times(1)).logDatabaseUpdateProductStockLevelSuccess();
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void failToUpdateProductStockLevelInDatabase() throws SQLStatementException {
        int expectedStockLevel = 1;
        int expectedProductId = 1;
        String expectedUpdateProductStockLevelStatement = updateProductStockLevelStatement(expectedProductId, expectedStockLevel);

        String expectedExceptionMessage = "Failure!";
        SQLStatementException expectedException = new SQLStatementException(expectedExceptionMessage, new SQLException());

        doThrow(expectedException).when(sqLiteExecute).executeUpdate(expectedUpdateProductStockLevelStatement);

        sqLiteSource.updateProductStockLevel(expectedProductId, expectedStockLevel);

        verify(loggingServiceMock, times(1)).logDatabaseUpdateProductStockLevelFailure(expectedUpdateProductStockLevelStatement, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    private SQLiteConnection createSQLiteConnection() {
        String testDatabaseUrl = getExpectedDatabaseUrl();
        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingServiceMock);
        sqLiteConnection.setDatabaseUrl(testDatabaseUrl);
        return sqLiteConnection;
    }

    private String getExpectedDatabaseUrl() {
        String expectedDatabasePath = "src/test/resources/database/";
        String expectedDatabaseName = "TestSecondSomervilleSwagDB.db";
        return "jdbc:sqlite:" + expectedDatabasePath + expectedDatabaseName;
    }

    private String getCustomerQuery(String email, String password) {
        return "SELECT * FROM Customer WHERE Email = '" + email + "' AND Password = '" + password + "';";
    }

    private String insertCustomerStatement() {
        return "INSERT INTO Customer (Forename, Surname, Email, Password, AddressLine1, AddressLine2, City, Postcode, PhoneNumber) " +
                "VALUES('testForename', 'testSurname', 'testEmail', 'testPassword', 'testAddressLine1', 'testAddressLine2', 'testCity', 'testPostcode', 'testPhoneNumber');";
    }

    private String updateProductStockLevelStatement(int productId, int stockLevel) {
        return "UPDATE Product SET StockLevel = " + stockLevel + " WHERE ProductId = " + productId + ";";
    }

    private List<String> getGuestData() {
        return new ArrayList<>() {
            {
                add("testForename");
                add("testSurname");
                add("testEmail");
                add("testPassword");
                add("testAddressLine1");
                add("testAddressLine2");
                add("testCity");
                add("testPostcode");
                add("testPhoneNumber");
            }
        };
    }

    private List<Product> getAllExpectedProducts() {
        return new ArrayList<>() {
            {
               add(new Product(1, "SomerSnow Globe", "Be the envy of your friends with this snazzy snowscene", new BigDecimal("20.0"), 5, "src/main/resources/product.images/derek_snowglobe.png"));
               add(new Product(2, "Derek of Cards", "Playing cards 100% tested and proven to improve BlackJack skills", new BigDecimal("7.0"), 5, "src/main/resources/product.images/derek_of_cards.png"));
               add(new Product(3, "Crushin it Crocs", "You will be the belle of the ball in these Somerville belters", new BigDecimal("40.0"), 5, "src/main/resources/product.images/derek_crocs.png"));
               add(new Product(4, "Head Bobble", "Non-stop groovin. It never gets old", new BigDecimal("16.0"), 5, "src/main/resources/product.images/derek_headbobble.png"));
               add(new Product(5, "SomerSpecs", "Yo dawg we heard you like glasses", new BigDecimal("27.0"), 5, "src/main/resources/product.images/derek_specs.png"));
               add(new Product(6, "SomerSocks", "Comes in various colours and all edge cases covered!", new BigDecimal("15.0"), 5, "src/main/resources/product.images/derek_socks.png"));
            }
        };
    }
}