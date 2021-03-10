package org.somerville.swag.data.service;

import org.somerville.swag.data.exeption.FileWriterException;

public interface LoggingService {
    void logDatabaseWriteSuccess(String insertSQLStatement) throws FileWriterException;
    void logDatabaseWriteFailure(String insertSQLStatement, String failureMessage) throws FileWriterException;
    void logDatabaseReadSuccess( String selectSQLStatement) throws FileWriterException;
    void logDatabaseReadFailure(String selectSQLStatement, String failureMessage) throws FileWriterException;
}
