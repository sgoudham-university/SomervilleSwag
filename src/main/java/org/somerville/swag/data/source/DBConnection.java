package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.SQLConnectionException;
import org.somerville.swag.data.service.LoggingService;

import java.sql.Connection;

public interface DBConnection {
    Connection connect(String databaseUrl) throws SQLConnectionException;
    Connection connect() throws SQLConnectionException;

    void setDatabaseUrl(String databaseUrl);
    void setLoggingService(LoggingService loggingService);
}
