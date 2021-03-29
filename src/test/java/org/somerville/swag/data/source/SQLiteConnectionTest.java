package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.exception.SQLConnectionException;
import org.somerville.swag.data.service.LoggingService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SQLiteConnectionTest {

    @Mock
    LoggingService loggingServiceMock;

    DBConnection sqLiteConnection;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingServiceMock);
    }

    @Test
    void successfullyReturnSameInstance() {
        DBConnection firstSqliteConnection = SQLiteConnection.getInstance();
        DBConnection secondSqliteConnection = SQLiteConnection.getInstance();

        assertSame(firstSqliteConnection, secondSqliteConnection);
    }

    @Test
    void successfullyConnectToDatabase() throws SQLConnectionException {
        String expectedDatabaseName = "TestFirstSomervilleSwagDB.db";
        String expectedDatabaseUrl = getExpectedDatabaseUrl(expectedDatabaseName);

        sqLiteConnection.setDatabaseUrl(expectedDatabaseUrl);
        sqLiteConnection.connect();

        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void successfullySwitchDatabaseConnection() throws SQLConnectionException {
        String expectedFirstDatabaseName = "TestFirstSomervilleSwagDB.db";
        String expectedSecondDatabaseName = "TestSecondSomervilleSwagDB.db";
        String expectedFirstDatabaseUrl = getExpectedDatabaseUrl(expectedFirstDatabaseName);
        String expectedSecondDatabaseUrl = getExpectedDatabaseUrl(expectedSecondDatabaseName);

        sqLiteConnection.setDatabaseUrl(expectedFirstDatabaseUrl);
        sqLiteConnection.connect();
        sqLiteConnection.setDatabaseUrl(expectedSecondDatabaseUrl);
        sqLiteConnection.connect();

        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedFirstDatabaseUrl);
        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedSecondDatabaseUrl);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void failToConnectToInvalidDatabase() {
        String expectedDatabaseUrl = "InvalidDatabase.db";

        String expectedExceptionMessage = "No suitable driver found for InvalidDatabase.db";
        SQLConnectionException expectedException = new SQLConnectionException(expectedExceptionMessage, new SQLException());

        sqLiteConnection.setDatabaseUrl(expectedDatabaseUrl);
        Throwable thrownException = assertThrows(SQLConnectionException.class, sqLiteConnection::connect);

        assertThat(thrownException.getMessage(), is(expectedException.getMessage()));
        verify(loggingServiceMock, times(1)).logDatabaseConnectFailure(expectedDatabaseUrl, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    private String getExpectedDatabaseUrl(String expectedDatabaseName) {
        String expectedDatabasePath = "src/test/resources/database/";
        return "jdbc:sqlite:" + expectedDatabasePath + expectedDatabaseName;
    }
}