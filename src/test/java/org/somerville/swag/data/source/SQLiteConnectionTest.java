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
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sqliteConnectionShouldReturnSameInstance() {
        DBConnection firstSqliteConnection = SQLiteConnection.getInstance();
        DBConnection secondSqliteConnection = SQLiteConnection.getInstance();

        assertSame(firstSqliteConnection, secondSqliteConnection);
    }

    @Test
    void successfullyConnectToDatabase() throws SQLConnectionException {
        String expectedDatabasePath = "src/test/resources/database/";
        String expectedDatabaseName = "TestFirstSomervilleSwagDB.db";
        String expectedDatabaseUrl = "jdbc:sqlite:" + expectedDatabasePath + expectedDatabaseName;
        String expectedSuccessMessage = "Successful Connection to Database: " + expectedDatabaseUrl;

        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingService);
        sqLiteConnection.setDatabaseUrl(expectedDatabaseUrl);

        sqLiteConnection.connect();

        verify(sqLiteConnection.getLoggingService(), times(1)).logDatabaseConnectSuccess(expectedSuccessMessage);
        verifyNoMoreInteractions(sqLiteConnection.getLoggingService());
    }

    @Test
    void successfullySwitchDatabaseConnection() throws SQLConnectionException {
        String expectedDatabasePath = "src/test/resources/database/";
        String expectedFirstDatabaseName = "TestFirstSomervilleSwagDB.db";
        String expectedSecondDatabaseName = "TestSecondSomervilleSwagDB.db";
        String expectedFirstDatabaseUrl = "jdbc:sqlite:" + expectedDatabasePath + expectedFirstDatabaseName;
        String expectedSecondDatabaseUrl = "jdbc:sqlite:" + expectedDatabasePath + expectedSecondDatabaseName;
        String expectedFirstSuccessMessage = "Successful Connection to Database: " + expectedFirstDatabaseUrl;
        String expectedSecondSuccessMessage = "Successful Connection to Database: " + expectedSecondDatabaseUrl;

        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingService);

        sqLiteConnection.setDatabaseUrl(expectedFirstDatabaseUrl);
        sqLiteConnection.connect();
        sqLiteConnection.setDatabaseUrl(expectedSecondDatabaseUrl);
        sqLiteConnection.connect();

        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedFirstSuccessMessage);
        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedSecondSuccessMessage);
        verifyNoMoreInteractions(loggingService);
    }

    @Test
    void successfullySwitchConnectionWithNewDatabaseUrl() throws SQLConnectionException {
        String expectedDatabasePath = "src/test/resources/database/";
        String expectedFirstDatabaseName = "TestFirstSomervilleSwagDB.db";
        String expectedSecondDatabaseName = "TestSecondSomervilleSwagDB.db";
        String expectedFirstDatabaseUrl = "jdbc:sqlite:" + expectedDatabasePath + expectedFirstDatabaseName;
        String expectedSecondDatabaseUrl = "jdbc:sqlite:" + expectedDatabasePath + expectedSecondDatabaseName;
        String expectedFirstSuccessMessage = "Successful Connection to Database: " + expectedFirstDatabaseUrl;
        String expectedSecondSuccessMessage = "Successful Connection to Database: " + expectedSecondDatabaseUrl;

        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setLoggingService(loggingService);

        sqLiteConnection.connect(expectedFirstDatabaseUrl);
        sqLiteConnection.setDatabaseUrlAndConnectTo(expectedSecondDatabaseUrl);

        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedFirstSuccessMessage);
        verify(loggingService, times(1)).logDatabaseConnectSuccess(expectedSecondSuccessMessage);
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
}