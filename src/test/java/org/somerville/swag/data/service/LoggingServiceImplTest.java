package org.somerville.swag.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.service.util.Clock;
import org.somerville.swag.data.source.MyFileWriter;
import org.somerville.swag.data.source.MyTextFileWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoggingServiceImplTest {

    @Mock
    Clock clock;

    @Mock
    MyTextFileWriter myTextFileWriter;

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
        doNothing().when(myTextFileWriter).writeToFile(expectedCurrentTime + expectedLogMessage, true);

        assertDoesNotThrow(() -> loggingService.writeLog(expectedLogMessage));

        verify(myTextFileWriter, times(1)).writeToFile(expectedCurrentTime + expectedLogMessage, true);
        verifyNoMoreInteractions(myTextFileWriter);
    }

    @Test
    void failToWriteToLog() throws FileWriterException {
        String expectedCurrentTime = "2010-04-23 12:12:12";
        String expectedLogMessage = "testLogMessage";

        loggingService.setClock(clock);
        loggingService.setTextFileWriter(myTextFileWriter);

        when(clock.getCurrentTime()).thenReturn(expectedCurrentTime);
        doNothing().when(myTextFileWriter).writeToFile(expectedCurrentTime + expectedLogMessage, true);

        assertDoesNotThrow(() -> loggingService.writeLog(expectedLogMessage));

        verify(myTextFileWriter, times(1)).writeToFile(expectedCurrentTime + expectedLogMessage, true);
        verifyNoMoreInteractions(myTextFileWriter);
    }
}