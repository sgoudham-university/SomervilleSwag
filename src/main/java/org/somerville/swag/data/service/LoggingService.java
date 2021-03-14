package org.somerville.swag.data.service;

public interface LoggingService {
    void logDatabaseWriteSuccess(String insertSQLStatement);
    void logDatabaseWriteFailure(String insertSQLStatement, String failureMessage);
    void logDatabaseReadSuccess(String selectSQLStatement);
    void logDatabaseReadFailure(String selectSQLStatement, String failureMessage);
    void logDatabaseConnectSuccess(String successMessage);
    void logDatabaseConnectFailure(String databaseUrl, String failureMessage);
}
