package org.somerville.swag.data.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSource implements DataSource{

    private DataExecute dataExecute = new DatabaseExecute();

    public void setDataExecute(DataExecute dataExecute) {
        this.dataExecute = dataExecute;
    }

    protected String getSelectQuery(String tableName){
        return "Select * from " + tableName;
    }

    private List<List<String>> getDataFromTable(ResultSet resultSet, String tableName, String[] columnNames) {
        List<List<String>> queryData = new ArrayList<List<String>>();
        ArrayList<String> queryRow = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                queryRow = new ArrayList<String>();;
                for (String columnName:columnNames){
                    queryRow.add(resultSet.getString(columnName));
                }
                queryData.add(queryRow);
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return queryData;
    }

    public List<List<String>> getDataFromTable( String tableName, String[] columnNames) {
        ResultSet resultSet = dataExecute.executeSelect(getSelectQuery(tableName));
        List<List<String>> queryData = getDataFromTable(resultSet,tableName,columnNames);
        return queryData;
    }

}
