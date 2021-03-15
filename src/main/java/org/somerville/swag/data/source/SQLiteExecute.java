package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.SQLConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExecute implements DBExecute {

    private DBConnection connection;

    public SQLiteExecute(DBConnection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public ResultSet executeSelect(String selectQuery) {
        ResultSet resultSet = null;
        try (Connection connection = this.connection.connect()) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
        } catch (SQLConnectionException | SQLException ioe) {
            //placeholder exception to be thrown - EH
        }
        return resultSet;
    }

    @Override
    public void executeInsert(String insertStatement) {
        try (Connection connection = this.connection.connect()) {
            Statement statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(insertStatement);
        } catch (SQLConnectionException | SQLException ioe) {
            //placeholder exception to be thrown - EH
        }
    }
}
