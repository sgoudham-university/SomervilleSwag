package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.SQLConnectionException;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.somerville.swag.data.source.util.Constants.DATABASE_URL;
import static org.somerville.swag.data.source.util.Constants.JDBC_URL;

/**
 * SQLiteConnection opens a valid connection to the specified database (Default - SomervilleSwag.db)
 */
public class SQLiteConnection implements DBConnection {

    private static SQLiteConnection instance;

    private String databaseUrl = JDBC_URL + DATABASE_URL;

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    private SQLiteConnection() { }

    public static SQLiteConnection getInstance() {
        return instance == null ? instance = new SQLiteConnection() : instance;
    }

    @Override
    public Connection connect() throws SQLConnectionException {
        return connect(databaseUrl);
    }

    @Override
    public Connection connect(String databaseUrl) throws SQLConnectionException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(databaseUrl);
            loggingService.logDatabaseConnectSuccess(databaseUrl);
        } catch (SQLException sqle) {
            loggingService.logDatabaseConnectFailure(databaseUrl, sqle.getMessage());
            throw new SQLConnectionException(sqle.getMessage(), sqle);
        }
        return connection;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }
}


