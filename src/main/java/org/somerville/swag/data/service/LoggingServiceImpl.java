package org.somerville.swag.data.service;

import org.apache.log4j.BasicConfigurator;
import org.somerville.swag.data.domain.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.somerville.swag.data.exeption.FileWriterException;
import org.somerville.swag.data.source.TextFileWriter;

public class LoggingServiceImpl implements LoggingService {

    private static LoggingServiceImpl instance;

    private final Events events = new Events();
    private final TextFileWriter textFileWriter = new TextFileWriter("logs.txt");

    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    private LoggingServiceImpl() { BasicConfigurator.configure(); }

    public static LoggingServiceImpl getInstance() {
        if (instance == null) {
            instance = new LoggingServiceImpl();
        }
        return instance;
    }

    @Override
    public void logDatabaseWriteSuccess(String insertSQLStatement) throws FileWriterException {
        String logMessage = events.getDatabaseWriteSuccess() + ": " + insertSQLStatement;
        logger.info(logMessage);
        textFileWriter.writeToFile(logMessage, true);
    }

    @Override
    public void logDatabaseWriteFailure(String insertSQLStatement, String failureMessage) throws FileWriterException {
        String logMessage  = events.getDatabaseWriteFailure() + ": " + failureMessage + " - Statement: " + insertSQLStatement;
        logger.info(logMessage);
        textFileWriter.writeToFile(logMessage, true);
    }

    @Override
    public void logDatabaseReadSuccess(String selectSQLStatement) throws FileWriterException {
        String logMessage = events.getDatabaseReadSuccess() + ": " + selectSQLStatement;
        logger.info(logMessage);
        textFileWriter.writeToFile(logMessage, true);
    }

    @Override
    public void logDatabaseReadFailure(String selectSQLStatement, String failureMessage) throws FileWriterException {
        String logMessage  = events.getDatabaseReadFailure() + ": " + failureMessage + " - Statement: " + selectSQLStatement;
        logger.info(logMessage);
        textFileWriter.writeToFile(logMessage, true);
    }
}
