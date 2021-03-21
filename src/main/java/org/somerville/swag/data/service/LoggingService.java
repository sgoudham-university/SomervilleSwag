package org.somerville.swag.data.service;

import org.slf4j.Logger;
import org.somerville.swag.data.service.util.Clock;
import org.somerville.swag.data.source.MyFileWriter;

public interface LoggingService {
    void logDatabaseConnectSuccess(String databaseName);
    void logDatabaseConnectFailure(String databaseUrl, String failureMessage);
    void logDatabaseCreateTablesSuccess(String fileName);
    void logDatabaseCreateTablesFailure(String fileName, String failureMessage);
    void logDatabasePopulateProductTableSuccess(String fileName);
    void logDatabasePopulateProductTableFailure(String fileName, String failureMessage);
    void logDatabaseInsertSuccess(String insertStatement, int rowsUpdated);
    void logDatabaseInsertFailure(String insertStatement, String failureMessage);
    void logDatabaseSelectSuccess(String selectQuery);
    void logDatabaseSelectFailure(String selectQuery, String failureMessage);

    void setClock(Clock clock);
    void setLogger(Logger logger);
    void setTextFileWriter(MyFileWriter textFileWriter);
    void writeLog(String logMessage);
}
