package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.exception.SQLStatementException;
import org.somerville.swag.data.service.LoggingService;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SQLiteExecuteTest {

    @Mock
    LoggingService loggingService;

    SQLiteExecute sqLiteExecute;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sqLiteExecute = new SQLiteExecute(createSQLiteConnection());
        sqLiteExecute.setLoggingService(loggingService);
    }

    @Test
    void successfullyReadFromDatabase() throws SQLStatementException {
        String expectedDatabaseUrl = getExpectedDatabaseUrl();
        String expectedQuery = "SELECT * FROM Customer WHERE CustomerId = 1;";

        sqLiteExecute.executeSelect(expectedQuery);

        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verify(loggingService, times(1)).logDatabaseSelectSuccess(expectedQuery);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void successfullyWriteToDatabase() throws SQLStatementException {
        String expectedDatabaseUrl = getExpectedDatabaseUrl();
        String expectedStatement = "INSERT INTO Customer (Forename, Surname, Email, Password, AddressLine1, AddressLine2, City, Postcode, PhoneNumber) " +
                "VALUES ('testForename', 'testSurname', 'testEmail', 'testPassword', 'testAddressLine1','testAddressLine2','testCity','testPostcode','testPhoneNumber');";
        int expectedRowsUpdated = 1;

        sqLiteExecute.executeUpdate(expectedStatement);

        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verify(loggingService, times(1)).logDatabaseInsertSuccess(expectedStatement, expectedRowsUpdated);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToReadFromDatabase() {
        String expectedDatabaseUrl = getExpectedDatabaseUrl();

        String expectedExceptionMessage = "[SQLITE_ERROR] SQL error or missing database (near \"WHERE\": syntax error)";
        SQLStatementException expectedException = new SQLStatementException(expectedExceptionMessage, new SQLException());

        String expectedQuery = "SELECT * FROM WHERE CustomerId = 1;";

        SQLStatementException thrownException = assertThrows(SQLStatementException.class, () -> sqLiteExecute.executeSelect(expectedQuery));

        assertThat(thrownException.getMessage(), is(expectedException.getMessage()));
        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verify(loggingService, times(1)).logDatabaseSelectFailure(expectedQuery, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToWriteToDatabase() {
        String expectedDatabaseUrl = getExpectedDatabaseUrl();

        String expectedExceptionMessage = "[SQLITE_ERROR] SQL error or missing database (near \";\": syntax error)";
        SQLStatementException expectedException = new SQLStatementException(expectedExceptionMessage, new SQLException());

        String expectedStatement = "INSERT INTO Customer;";

        SQLStatementException thrownException = assertThrows(SQLStatementException.class, () -> sqLiteExecute.executeUpdate(expectedStatement));

        assertThat(thrownException.getMessage(), is(expectedException.getMessage()));
        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verify(loggingService, times(1)).logDatabaseInsertFailure(expectedStatement, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingService);
    }

    private SQLiteConnection createSQLiteConnection() {
        String testDatabaseUrl = "jdbc:sqlite:src/test/resources/database/TestFirstSomervilleSwagDB.db";
        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setDatabaseUrl(testDatabaseUrl);
        sqLiteConnection.setLoggingService(loggingService);
        return sqLiteConnection;
    }

    private String getExpectedDatabaseUrl() {
        String expectedDatabasePath = "src/test/resources/database/";
        String expectedDatabaseName = "TestFirstSomervilleSwagDB.db";
        return "jdbc:sqlite:" + expectedDatabasePath + expectedDatabaseName;
    }
}