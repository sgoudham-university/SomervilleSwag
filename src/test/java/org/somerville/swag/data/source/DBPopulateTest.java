package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.service.LoggingService;

import static org.mockito.Mockito.*;

class DBPopulateTest {

    DBPopulate dbPopulate;

    @Mock
    LoggingService loggingServiceMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        dbPopulate = spy(new DBPopulate(createSQLiteConnection()));
        dbPopulate.setLoggingService(loggingServiceMock);
    }

    @Test
    void successfullyRunCreateTablesScript() {
        String expectedLogConnectionSuccessMessage = getExpectedLogDatabaseSuccessfulConnection();
        String expectedFileName = "CreateTables.sql";
        String expectedCreateTablesScript = "src/main/resources/database/" + expectedFileName;

        dbPopulate.setCreateTablesScript(expectedCreateTablesScript);
        dbPopulate.createTables();

        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedLogConnectionSuccessMessage);
        verify(loggingServiceMock, times(1)).logDatabaseCreateTablesSuccess(expectedCreateTablesScript);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void successfullyPopulateProductsTable() {
        String expectedLogConnectionSuccessMessage = getExpectedLogDatabaseSuccessfulConnection();
        String expectedFileName = "PopulateProductTable.sql";
        String expectedPopulateProductTableScript = "src/main/resources/database/" + expectedFileName;

        dbPopulate.setPopulateProductTableScript(expectedPopulateProductTableScript);
        dbPopulate.populateProductTable();

        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedLogConnectionSuccessMessage);
        verify(loggingServiceMock, times(1)).logDatabasePopulateProductTableSuccess(expectedPopulateProductTableScript);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void failToCreateTablesWithInvalidFile() {
        String expectedLogConnectionSuccessMessage = getExpectedLogDatabaseSuccessfulConnection();
        String expectedInvalidFileName = "";
        String expectedScriptPath = "src\\main\\resources\\database";
        String expectedCreateTablesScript = expectedScriptPath + expectedInvalidFileName;
        String expectedExceptionMessage = getExpectedSystemExceptionMessage(expectedScriptPath);

        dbPopulate.setCreateTablesScript(expectedCreateTablesScript);
        dbPopulate.createTables();

        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedLogConnectionSuccessMessage);
        verify(loggingServiceMock, times(1)).logDatabaseCreateTablesFailure(expectedCreateTablesScript, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    @Test
    void failToPopulateProductTableWithInvalidFile() {
        String expectedLogConnectionSuccessMessage = getExpectedLogDatabaseSuccessfulConnection();
        String expectedInvalidFileName = "";
        String expectedScriptPath = "src\\main\\resources\\database";
        String expectedPopulateProductTableScript = expectedScriptPath + expectedInvalidFileName;
        String expectedExceptionMessage = getExpectedSystemExceptionMessage(expectedScriptPath);

        dbPopulate.setPopulateProductTableScript(expectedPopulateProductTableScript);
        dbPopulate.populateProductTable();

        verify(loggingServiceMock, times(1)).logDatabaseConnectSuccess(expectedLogConnectionSuccessMessage);
        verify(loggingServiceMock, times(1)).logDatabasePopulateProductTableFailure(expectedPopulateProductTableScript, expectedExceptionMessage);
        verifyNoMoreInteractions(loggingServiceMock);
    }

    private String getExpectedLogDatabaseSuccessfulConnection() {
        String expectedDatabasePath = "src/test/resources/database/";
        String expectedDatabaseName = "TestFirstSomervilleSwagDB.db";
        return "jdbc:sqlite:" + expectedDatabasePath + expectedDatabaseName;
    }

    private String getExpectedSystemExceptionMessage(String expectedScriptPath) {
        String expectedExceptionMessage;
        if (System.getProperty("os.name").equals("Windows 10")) {
            expectedExceptionMessage = expectedScriptPath + " (Access is denied)";
        } else {
            expectedExceptionMessage = expectedScriptPath + " (No such file or directory)";
        }
        return expectedExceptionMessage;
    }

    private SQLiteConnection createSQLiteConnection() {
        String testDatabaseUrl = "jdbc:sqlite:src/test/resources/database/TestFirstSomervilleSwagDB.db";
        SQLiteConnection sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setDatabaseUrl(testDatabaseUrl);
        sqLiteConnection.setLoggingService(loggingServiceMock);
        return sqLiteConnection;
    }
}