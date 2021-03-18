package org.somerville.swag.data.service;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LoggingServiceImplTest {

    @Test
    void dateTimeFormatsReturnsCorrectString()  {
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.APRIL,23,12,12,12);

        String pattern = "yyyy-MM-dd HH:mm:ss";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formatDateTime = localDateTime.format(formatter);

        String fakeDateTime = "2010-04-23 12:12:12";
        assertEquals(formatDateTime, fakeDateTime);
    }

}