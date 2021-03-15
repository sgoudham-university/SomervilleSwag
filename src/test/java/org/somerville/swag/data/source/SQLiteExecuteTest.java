package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.exception.SQLConnectionException;
import org.somerville.swag.data.exception.SQLStatementException;
import org.somerville.swag.data.service.LoggingService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteExecuteTest {

    private SQLiteConnection sqLiteConnection;
    private SQLiteExecute sqLiteExecute;

    @Mock
    LoggingService loggingService;

    @BeforeEach
    void setUp() throws SQLConnectionException, SQLException {
        sqLiteExecute = new SQLiteExecute(createDatabaseConnection());
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void successfullyReadFromDatabase() throws SQLStatementException {
        String expectedQuery = "SELECT * FROM Customer WHERE CustomerId = 1;";
        ResultSet customerData = sqLiteExecute.executeSelect(expectedQuery);
        System.out.println(customerData);


    }

    @Test
    void successfullyWriteToDatabase() {

    }

    @Test
    void failToReadFromDatabase() {

    }

    @Test
    void failToWriteToDatabase() {

    }

    private SQLiteConnection createDatabaseConnection() {
        String testDatabaseUrl = "jdbc:sqlite:src/test/resources/database/TestFirstSomervilleSwagDB.db";
        sqLiteConnection = SQLiteConnection.getInstance();
        sqLiteConnection.setDatabaseUrl(testDatabaseUrl);
        return sqLiteConnection;

    }
}