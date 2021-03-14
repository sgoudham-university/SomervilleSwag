package org.somerville.swag.data.service;

import org.somerville.swag.data.exception.FileWriterException;

public interface LoggingService {
    void logDatabaseWriteSuccess(String insertSQLStatement) throws FileWriterException;
    void logDatabaseWriteFailure(String insertSQLStatement, String failureMessage) throws FileWriterException;
    void logDatabaseReadSuccess(String selectSQLStatement) throws FileWriterException;
    void logDatabaseReadFailure(String selectSQLStatement, String failureMessage) throws FileWriterException;
    void logDatabaseConnectSuccess(String successMessage) throws FileWriterException;
    void logDatabaseConnectFailure(String databaseUrl, String failureMessage) throws FileWriterException;
}
