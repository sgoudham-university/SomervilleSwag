package org.somerville.swag.data.service.util;

public class ClockStub implements Clock {

    @Override
    public String getCurrentTime() {
        return "2010-04-23 12:12:12";
    }
}
