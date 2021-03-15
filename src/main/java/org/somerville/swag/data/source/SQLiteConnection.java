package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.DatabaseException;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.somerville.swag.data.source.util.Constants.*;

public class SQLiteConnection implements DBConnection {

    private static SQLiteConnection instance;
    private Connection connection;
    private String databaseUrl = JDBC_URL + DATABASE_URL;

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    private SQLiteConnection() { }

    public static SQLiteConnection getInstance() {
        return instance == null ? instance = new SQLiteConnection() : instance;
    }

    @Override
    public Connection connect() throws DatabaseException {
        return connect(databaseUrl);
    }

    @Override
    public Connection connect(String databaseUrl) throws DatabaseException {
        connection = null;
        try{
            connection = DriverManager.getConnection(databaseUrl);
            loggingService.logDatabaseConnectSuccess("Successful Connection to Database: " + databaseUrl);
        } catch (SQLException sqle) {
            loggingService.logDatabaseConnectFailure(databaseUrl, sqle.getMessage());
            throw new DatabaseException(sqle.getMessage(), sqle);
        }
        return connection;
    }

    public void setDatabaseUrlAndConnectTo(String databaseUrl) throws DatabaseException {
        this.databaseUrl = databaseUrl;
        connect(this.databaseUrl);
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public LoggingService getLoggingService() {
        return loggingService;
    }
}


