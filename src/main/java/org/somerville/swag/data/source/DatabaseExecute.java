package org.somerville.swag.data.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExecute implements DataExecute {

    private Connection connection;

    DatabaseExecute(){
        connection = getConnection();
    }

    private Connection getConnection(){
        String databaseUrlSuffix = "src/main/resources/applicationDB.db";
        Connection connection = DatabaseConnection.createDatabaseConnection(databaseUrlSuffix);
        return connection;
    }
    public ResultSet executeSelect(String selectQuery){
        ResultSet resultSet;
        try  {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return resultSet;
    }

}
