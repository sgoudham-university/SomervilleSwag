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
    public void logDatabaseWriteSuccess(String insertSQLStatement) throws FileWriterException {
        String logMessage = events.getDatabaseWriteSuccess() + ": " + insertSQLStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseWriteFailure(String insertSQLStatement, String failureMessage) throws FileWriterException {
        String logMessage = events.getDatabaseWriteFailure() + ": " + failureMessage + " - Statement: " + insertSQLStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseReadSuccess(String selectSQLStatement) throws FileWriterException {
        String logMessage = events.getDatabaseReadSuccess() + ": " + selectSQLStatement;
        writeLog(logMessage);
    }

    @Override
    public void logDatabaseReadFailure(String selectSQLStatement, String failureMessage) throws FileWriterException {
        String logMessage  = events.getDatabaseReadFailure() + ": " + failureMessage + " - Statement: " + selectSQLStatement;
        writeLog(logMessage);
    }

    private void writeLog(String logMessage) throws FileWriterException {
        logger.info(logMessage);
        textFileWriter.writeToFile(logMessage, true);
    }
}
