package org.somerville.swag.data.service;

public interface LoggingService {
    void logDatabaseConnectSuccess(String successMessage);
    void logDatabaseConnectFailure(String databaseUrl, String failureMessage);
    void logDatabaseCreateTablesSuccess(String fileName);
    void logDatabaseCreateTablesFailure(String fileName, String failureMessage);
    void logDatabasePopulateProductTableSuccess(String fileName);
    void logDatabasePopulateProductTableFailure(String fileName, String failureMessage);
    void logDatabaseInsertSuccess(String insertStatement, int rowsUpdated);
    void logDatabaseInsertFailure(String insertStatement, String failureMessage);
    void logDatabaseSelectSuccess(String selectQuery);
    void logDatabaseSelectFailure(String selectQuery, String failureMessage);
}
