package org.somerville.swag.data.service;

import org.apache.log4j.BasicConfigurator;
import org.somerville.swag.data.domain.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.service.util.Clock;
import org.somerville.swag.data.service.util.ClockImpl;
import org.somerville.swag.data.source.MyFileWriter;
import org.somerville.swag.data.source.MyTextFileWriter;

public class LoggingServiceImpl implements LoggingService {

    private static LoggingServiceImpl instance;

    private Clock clock = new ClockImpl();

    private final Events events = new Events();
    private MyFileWriter textFileWriter = new MyTextFileWriter();

    private Logger logger = LoggerFactory.getLogger(LoggingService.class);

    private LoggingServiceImpl() { BasicConfigurator.configure(); }

    public static LoggingServiceImpl getInstance() {
        return instance == null ? instance = new LoggingServiceImpl() : instance;
    }

    @Override
    public void logDatabaseConnectSuccess(String databaseUrl) {
        String logMessage = events.getDatabaseConnectSuccess() + ": " + databaseUrl;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseConnectFailure(String databaseUrl, String failureMessage) {
        String logMessage  = events.getDatabaseConnectFailure() + ": " + failureMessage + " - Database: " + databaseUrl;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseCreateTablesSuccess(String fileName) {
        String logMessage = events.getDatabaseCreateTablesSuccess() + ": " + fileName;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseCreateTablesFailure(String fileName, String failureMessage) {
        String logMessage  = events.getDatabaseCreateTablesFailure() + ": " + failureMessage + " - Script Name: " + fileName;
        writeLog(logMessage);
    }

    @Override
    public void logDatabasePopulateProductTableSuccess(String fileName) {
        String logMessage = events.getDatabasePopulateProductTableSuccess() + ": " + fileName;
        writeLog(logMessage);
    }

    @Override
    public void logDatabasePopulateProductTableFailure(String fileName, String failureMessage) {
        String logMessage  = events.getDatabasePopulateProductTableFailure() + ": " + failureMessage + " - Script Name: " + fileName;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseInsertSuccess(String insertStatement, int rowsUpdated) {
        String logMessage = events.getDatabaseWriteSuccess() + " - Rows Updated: " + rowsUpdated + " - " + insertStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseInsertFailure(String insertStatement, String failureMessage) {
        String logMessage = events.getDatabaseWriteFailure() + ": " + failureMessage + " - Statement: " + insertStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseSelectSuccess(String selectQuery) {
        String logMessage = events.getDatabaseReadSuccess() + ": " + selectQuery;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseSelectFailure(String selectQuery, String failureMessage) {
        String logMessage  = events.getDatabaseReadFailure() + ": " + failureMessage + " - Statement: " + selectQuery;
        writeLog(logMessage);
    }

    @Override
    public void writeLog(String logMessage) {
        String actualLogMessage = "[" + clock.getCurrentTime() + "] " + logMessage;
        logger.info(actualLogMessage);
        try {
            textFileWriter.writeToFile(actualLogMessage, true);
        } catch (FileWriterException fwe) {
            logger.info(fwe.getMessage());
        }
    }

    @Override
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void setLogger(Logger logger) { this.logger = logger; }

    @Override
    public void setTextFileWriter(MyFileWriter textFileWriter) {
        this.textFileWriter = textFileWriter;
    }

}
