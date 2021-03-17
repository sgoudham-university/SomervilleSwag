package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.SQLConnectionException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.somerville.swag.data.source.util.Constants.CREATE_TABLES_SCRIPT;
import static org.somerville.swag.data.source.util.Constants.POPULATE_TABLES_SCRIPT;

public class DBPopulate {

    private SQLiteConnection sqLiteConnection;
    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public DBPopulate(SQLiteConnection sqLiteConnection) {
        this.sqLiteConnection = sqLiteConnection;
    }

    public void createTables() {
        try (Connection connection = sqLiteConnection.connect()) {
            runScript(CREATE_TABLES_SCRIPT, connection);
            loggingService.logDatabaseCreateTablesSuccess(CREATE_TABLES_SCRIPT);
        } catch (FileNotFoundException | SQLConnectionException | SQLException err) {
            loggingService.logDatabaseCreateTablesFailure(CREATE_TABLES_SCRIPT, err.getMessage());
        }
    }

    public void populateProductsTable() {
        try (Connection connection = sqLiteConnection.connect()) {
            runScript(POPULATE_TABLES_SCRIPT, connection);
            loggingService.logDatabasePopulateTablesSuccess(POPULATE_TABLES_SCRIPT);
        } catch (FileNotFoundException | SQLConnectionException | SQLException err) {
            loggingService.logDatabasePopulateTablesFailure(POPULATE_TABLES_SCRIPT, err.getMessage());
        }
    }

    private void runScript(String fileName, Connection connection) throws FileNotFoundException {
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader(fileName));
        scriptRunner.setEscapeProcessing(false);
        scriptRunner.runScript(reader);
    }
}
