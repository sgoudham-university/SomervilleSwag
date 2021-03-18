package org.somerville.swag.data.service.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockImpl implements Clock {

    private final String pattern = "yyyy-MM-dd HH:mm:ss";

    public String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }
}
