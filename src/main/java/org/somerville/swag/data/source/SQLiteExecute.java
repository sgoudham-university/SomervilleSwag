package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.SQLConnectionException;
import org.somerville.swag.data.exception.SQLStatementException;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExecute implements DBExecute {

    private DBConnection connection;

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public SQLiteExecute(DBConnection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet executeSelect(String selectQuery) throws SQLStatementException {
        ResultSet resultSet;
        try {
            Statement statement = connection.connect().createStatement();
            resultSet = statement.executeQuery(selectQuery);
            loggingService.logDatabaseSelectSuccess(selectQuery);
        } catch (SQLConnectionException | SQLException err) {
            loggingService.logDatabaseSelectFailure(selectQuery, err.getMessage());
            throw new SQLStatementException(err.getMessage(), err);
        }
        return resultSet;
    }

    @Override
    public int executeInsert(String insertStatement) throws SQLStatementException {
        int rowsUpdated = 0;
        try (Statement statement = connection.connect().createStatement()) {
            rowsUpdated = statement.executeUpdate(insertStatement);
            loggingService.logDatabaseInsertSuccess(insertStatement, rowsUpdated);
        } catch (SQLConnectionException | SQLException err) {
            loggingService.logDatabaseInsertFailure(insertStatement, err.getMessage());
            throw new SQLStatementException(err.getMessage(), err);
        }
        return rowsUpdated;
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }
}
