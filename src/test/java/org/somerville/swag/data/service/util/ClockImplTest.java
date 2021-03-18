package org.somerville.swag.data.service.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ClockImplTest {

    @Test
    void successfullyGetCurrentTimeFromStub() {
        String expectedCurrentTime = "2010-04-23 12:12:12";

        Clock actualClock = new ClockStub();
        String actualCurrentTime = actualClock.getCurrentTime();

        assertThat(actualCurrentTime, is(expectedCurrentTime));
    }
}