package org.somerville.swag.data.service;

import org.apache.log4j.BasicConfigurator;
import org.somerville.swag.data.domain.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.source.MyFileWriter;
import org.somerville.swag.data.source.MyTextFileWriter;

public class LoggingServiceImpl implements LoggingService {

    private static LoggingServiceImpl instance;

    private final Events events = new Events();
    private final MyFileWriter textFileWriter = new MyTextFileWriter();

    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    private LoggingServiceImpl() { BasicConfigurator.configure(); }

    public static LoggingServiceImpl getInstance() {
        if (instance == null) {
            instance = new LoggingServiceImpl();
        }
        return instance;
    }

    @Override
    public void logDatabaseWriteSuccess(String insertSQLStatement) {
        String logMessage = events.getDatabaseWriteSuccess() + ": " + insertSQLStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseWriteFailure(String insertSQLStatement, String failureMessage) {
        String logMessage = events.getDatabaseWriteFailure() + ": " + failureMessage + " - Statement: " + insertSQLStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseReadSuccess(String selectSQLStatement) {
        String logMessage = events.getDatabaseReadSuccess() + ": " + selectSQLStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseReadFailure(String selectSQLStatement, String failureMessage) {
        String logMessage  = events.getDatabaseReadFailure() + ": " + failureMessage + " - Statement: " + selectSQLStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseConnectSuccess(String successMessage) {
        String logMessage = events.getDatabaseConnectSuccess() + ": " + successMessage;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseConnectFailure(String databaseUrl, String failureMessage) {
        String logMessage  = events.getDatabaseConnectFailure() + ": " + failureMessage + " - Database: " + databaseUrl;
        writeLog(logMessage);
    }

    private void writeLog(String logMessage) {
        logger.info(logMessage);
        try {
            textFileWriter.writeToFile(logMessage, true);
        } catch (FileWriterException fwe) {
            logger.info(fwe.getMessage());
        }
    }
}
