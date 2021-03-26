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
    LoggingService loggingService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
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

        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingService);
        sqLiteConnection.setDatabaseUrl(expectedDatabaseUrl);

        sqLiteConnection.connect();

        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedDatabaseUrl);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void successfullySwitchDatabaseConnection() throws SQLConnectionException {
        String expectedFirstDatabaseName = "TestFirstSomervilleSwagDB.db";
        String expectedSecondDatabaseName = "TestSecondSomervilleSwagDB.db";
        String expectedFirstDatabaseUrl = getExpectedDatabaseUrl(expectedFirstDatabaseName);
        String expectedSecondDatabaseUrl = getExpectedDatabaseUrl(expectedSecondDatabaseName);

        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingService);

        sqLiteConnection.setDatabaseUrl(expectedFirstDatabaseUrl);
        sqLiteConnection.connect();
        sqLiteConnection.setDatabaseUrl(expectedSecondDatabaseUrl);
        sqLiteConnection.connect();

        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedFirstDatabaseUrl);
        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedSecondDatabaseUrl);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void successfullySwitchConnectionWithNewDatabaseUrl() throws SQLConnectionException {
        String expectedFirstDatabaseName = "TestFirstSomervilleSwagDB.db";
        String expectedSecondDatabaseName = "TestSecondSomervilleSwagDB.db";
        String expectedFirstDatabaseUrl = getExpectedDatabaseUrl(expectedFirstDatabaseName);
        String expectedSecondDatabaseUrl = getExpectedDatabaseUrl(expectedSecondDatabaseName);

        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingService);

        sqLiteConnection.connect(expectedFirstDatabaseUrl);
        sqLiteConnection.setDatabaseUrlAndConnectTo(expectedSecondDatabaseUrl);

        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedFirstDatabaseUrl);
        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedSecondDatabaseUrl);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void failToConnectToInvalidDatabase() {
        String expectedDatabaseUrl = "InvalidDatabase.db";

        String expectedExceptionMessage = "No suitable driver found for InvalidDatabase.db";
        SQLConnectionException expectedException = new SQLConnectionException(expectedExceptionMessage, new SQLException());

        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingService);
        sqLiteConnection.setDatabaseUrl(expectedDatabaseUrl);

        SQLConnectionException thrownException = assertThrows(SQLConnectionException.class, sqLiteConnection::connect);

        assertThat(thrownException.getMessage(), is(expectedException.getMessage()));
        verify(loggingService, times(1)).logDatabaseConnectFailure(expectedDatabaseUrl, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingService);
    }

    private String getExpectedDatabaseUrl(String expectedDatabaseName) {
        String expectedDatabasePath = "src/test/resources/database/";
        return "jdbc:sqlite:" + expectedDatabasePath + expectedDatabaseName;
    }
}