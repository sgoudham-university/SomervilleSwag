package org.somerville.swag.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.service.util.Clock;
import org.somerville.swag.data.source.MyTextFileWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoggingServiceImplTest {

    @Mock
    Clock clock;

    @Mock
    MyTextFileWriter myTextFileWriter;

    @Mock
    Logger logger;

    LoggingServiceImpl loggingService = LoggingServiceImpl.getInstance();

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void successfullyWriteToLog() throws FileWriterException {
        String expectedCurrentTime = "2010-04-23 12:12:12";
        String expectedLogMessage = "testLogMessage";

        loggingService.setClock(clock);
        loggingService.setTextFileWriter(myTextFileWriter);

        when(clock.getCurrentTime()).thenReturn(expectedCurrentTime);
        doNothing().when(myTextFileWriter).writeToFile(expectedCurrentTime + " " + expectedLogMessage, true);

        assertDoesNotThrow(() -> loggingService.writeLog(expectedLogMessage));

        verify(clock, times(1)).getCurrentTime();
        verify(myTextFileWriter, times(1)).writeToFile(expectedCurrentTime + " " + expectedLogMessage, true);
        verifyNoMoreInteractions(clock);
        verifyNoMoreInteractions(myTextFileWriter);

    }

    @Test
    void failToWriteToLog() throws FileWriterException {
        String expectedCurrentTime = "2010-04-23 12:12:12";
        String expectedLogMessage = "testLogMessage";

        String expectedExceptionMessage = "fail to write";
        FileWriterException expectedException = new FileWriterException(expectedExceptionMessage, new IOException());

        loggingService.setLogger(logger);
        loggingService.setClock(clock);
        loggingService.setTextFileWriter(myTextFileWriter);

        when(clock.getCurrentTime()).thenReturn(expectedCurrentTime);
        doThrow(expectedException).when(myTextFileWriter).writeToFile(expectedCurrentTime + " " + expectedLogMessage, true);

        loggingService.writeLog(expectedLogMessage);

        verify(logger, times(1)).info(expectedCurrentTime + " " + expectedLogMessage);
        verify(logger, times(1)).info(expectedExceptionMessage);
        verifyNoMoreInteractions(logger);
    }
}