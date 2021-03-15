package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.exception.SQLStatementException;

import java.sql.ResultSet;

public interface DBExecute {
    ResultSet executeSelect(String selectQuery) throws FileWriterException, SQLStatementException;
    void executeInsert(String insertStatement) throws SQLStatementException;
}
