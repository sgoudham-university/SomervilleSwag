package org.somerville.swag.data.service;

public interface LoggingService {
    void logDatabaseInsertSuccess(String insertStatement, int rowsUpdated);
    void logDatabaseInsertFailure(String insertStatement, String failureMessage);
    void logDatabaseSelectSuccess(String selectQuery);
    void logDatabaseSelectFailure(String selectQuery, String failureMessage);
    void logDatabaseConnectSuccess(String successMessage);
    void logDatabaseConnectFailure(String databaseUrl, String failureMessage);
}
