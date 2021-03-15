package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.FileWriterException;

import java.sql.ResultSet;

public interface DBExecute {
    ResultSet executeSelect(String selectQuery) throws FileWriterException;
    void executeInsert(String insertStatement);
}
