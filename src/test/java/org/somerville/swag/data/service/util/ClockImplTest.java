package org.somerville.swag.data.service.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ClockImplTest {

    @Test
    void successfullyGetCurrentTimeFromStub() {
        String expectedPattern = "yyyy-MM-dd HH:mm:ss.SSSSSS";
        String expectedCurrentTime = "2010-04-23 12:12:12.111111";

        Clock actualClock = new ClockStub();
        String actualPattern = actualClock.getPattern();
        String actualCurrentTime = actualClock.getCurrentTime();

        assertThat(actualPattern, is(expectedPattern));
        assertThat(actualCurrentTime, is(expectedCurrentTime));
    }
}