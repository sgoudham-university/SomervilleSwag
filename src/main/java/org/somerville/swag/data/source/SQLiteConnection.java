package org.somerville.swag.data.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.somerville.swag.data.exception.DatabaseException;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.somerville.swag.data.source.util.Constants.DATABASE_URL;
import static org.somerville.swag.data.source.util.Constants.JDBC_URL;

public class SQLiteConnection implements DBConnection {

    private static SQLiteConnection instance;
    private Connection connection;
    private String databaseUrl = JDBC_URL + DATABASE_URL;

    private static final LoggingService loggingService = LoggingServiceImpl.getInstance();

    private SQLiteConnection() throws DatabaseException {
        connect(databaseUrl);
    }

    public static SQLiteConnection getInstance() throws DatabaseException {
        if (instance == null) {
            instance = new SQLiteConnection();
        }
        return instance;
    }

    @Override
    public Connection connect(String databaseUrl) throws DatabaseException {
        try{
            connection = DriverManager.getConnection(databaseUrl);
            loggingService.logDatabaseConnectSuccess("Connection successful to database: " + databaseUrl);
        } catch (SQLException sqle) {
            loggingService.logDatabaseConnectFailure(databaseUrl, sqle.getMessage());
            throw new DatabaseException(sqle.getMessage(), sqle);
        }
        return connection;
    }

    public void setDatabaseUrl(String databaseUrl) throws DatabaseException {
        this.databaseUrl = databaseUrl;
        connect(databaseUrl);
    }
}


