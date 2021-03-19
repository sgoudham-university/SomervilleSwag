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

    private String populateProductTableScript = POPULATE_TABLES_SCRIPT;
    private String createTablesScript = CREATE_TABLES_SCRIPT;

    private DBConnection connection;

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public DBPopulate(DBConnection connection) {
        this.connection = connection;
    }

    public void createTables() {
        try (Connection conn = connection.connect()) {
            runScript(createTablesScript, conn);
            loggingService.logDatabaseCreateTablesSuccess(createTablesScript);
        } catch (FileNotFoundException | SQLConnectionException | SQLException err) {
            loggingService.logDatabaseCreateTablesFailure(createTablesScript, err.getMessage());
        }
    }

    public void populateProductTable() {
        try (Connection conn = connection.connect()) {
            runScript(populateProductTableScript, conn);
            loggingService.logDatabasePopulateProductTableSuccess(populateProductTableScript);
        } catch (FileNotFoundException | SQLConnectionException | SQLException err) {
            loggingService.logDatabasePopulateProductTableFailure(populateProductTableScript, err.getMessage());
        }
    }

    public void runScript(String fileName, Connection connection) throws FileNotFoundException {
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader(fileName));
        scriptRunner.setEscapeProcessing(false);
        scriptRunner.runScript(reader);
    }

    public void setCreateTablesScript(String createTablesScript) {
        this.createTablesScript = createTablesScript;
    }

    public void setPopulateProductTableScript(String populateProductTableScript) {
        this.populateProductTableScript = populateProductTableScript;
    }

    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public DBConnection getConnection() {
        return connection;
    }
}
