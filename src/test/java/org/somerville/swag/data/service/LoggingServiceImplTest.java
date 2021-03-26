package org.somerville.swag.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.service.util.Clock;
import org.somerville.swag.data.service.util.ClockStub;
import org.somerville.swag.data.source.MyTextFileWriter;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class LoggingServiceImplTest {

    @Mock
    MyTextFileWriter myTextFileWriter;

    @Mock
    Logger logger;

    @Spy
    LoggingService loggingService = LoggingServiceImpl.getInstance();

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successfullyReturnSameInstance() {
        LoggingService firstLoggingService = LoggingServiceImpl.getInstance();
        LoggingService secondLoggingService = LoggingServiceImpl.getInstance();

        assertSame(firstLoggingService, secondLoggingService);
    }

    @Test
    void successfullyLogDatabaseConnectSuccess() {
        String expectedDatabaseUrl = "jdbc:sqlite:src/test/resources/database/TestFirstSomervilleSwagDB.db";
        String expectedLogMessage = "org.somerville.swag.database_connect_success: " + expectedDatabaseUrl;

        doNothing().when(loggingService).writeLog(expectedLogMessage);

        loggingService.logDatabaseConnectSuccess(expectedDatabaseUrl);

        verify(loggingService, times(1)).writeLog(expectedLogMessage);
    }

    @Test
    void successfullyWriteToLog() throws FileWriterException {
        String expectedCurrentTime = "2010-04-23 12:12:12.111111";
        String expectedLogMessage = "testLogMessage";
        String expectedFullLogMessage = "[" + expectedCurrentTime + "] " + expectedLogMessage;
        Clock clockStub = new ClockStub();

        loggingService.setClock(clockStub);
        loggingService.setTextFileWriter(myTextFileWriter);

        doNothing().when(myTextFileWriter).writeToFile(expectedFullLogMessage, true);

        assertDoesNotThrow(() -> loggingService.writeLog(expectedLogMessage));

        verify(loggingService, times(1)).retrieveLogMessage(expectedLogMessage);
        verify(myTextFileWriter, times(1)).writeToFile(expectedFullLogMessage, true);
        verifyNoMoreInteractions(myTextFileWriter);
    }

    @Test
    void failToWriteToLog() throws FileWriterException {
        String expectedCurrentTime = "2010-04-23 12:12:12.111111";
        String expectedLogMessage = "testLogMessage";
        String expectedFullLogMessage = "[" + expectedCurrentTime + "] " + expectedLogMessage;
        Clock clockStub = new ClockStub();

        String expectedExceptionMessage = "Failure!";
        FileWriterException expectedException = new FileWriterException(expectedExceptionMessage, new IOException());

        loggingService.setLogger(logger);
        loggingService.setClock(clockStub);
        loggingService.setTextFileWriter(myTextFileWriter);

        doThrow(expectedException).when(myTextFileWriter).writeToFile(expectedFullLogMessage, true);

        loggingService.writeLog(expectedLogMessage);

        verify(logger, times(1)).info(expectedFullLogMessage);
        verify(logger, times(1)).info(expectedExceptionMessage);
        verifyNoMoreInteractions(logger);
    }

    @Test
    void successfullyRetrieveLogMessage() {
        String expectedCurrentTime = "2010-04-23 12:12:12.111111";
        String expectedLogMessage = "testLogMessage";
        String expectedFullLogMessage = "[" + expectedCurrentTime + "] " + expectedLogMessage;
        Clock clockStub = new ClockStub();

        loggingService.setClock(clockStub);

        String actualFullLogMessage = loggingService.retrieveLogMessage(expectedLogMessage);

        assertThat(actualFullLogMessage, is(expectedFullLogMessage));
    }

}