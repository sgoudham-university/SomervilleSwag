package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.somerville.swag.data.source.util.Constants.DATABASE_URL;
import static org.somerville.swag.data.source.util.Constants.JDBC_URL;

public class SQLiteConnection implements DBConnection {

    private static SQLiteConnection instance;
    private Connection connection;
    private String databaseUrl = JDBC_URL + DATABASE_URL;

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
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle);
        }
        return connection;
    }

    public void setDatabaseUrl(String databaseUrl) throws DatabaseException {
        this.databaseUrl = databaseUrl;
        connect(databaseUrl);
    }
}


