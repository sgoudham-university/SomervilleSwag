package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.SQLStatementException;

import java.sql.ResultSet;

public interface DBExecute {
    ResultSet executeSelect(String selectQuery) throws SQLStatementException;
    void executeInsert(String insertStatement) throws SQLStatementException;
}
