package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.somerville.swag.data.exception.FileWriterException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MyTextFileWriterTest {

    private MyFileWriter myFileWriter;

    @BeforeEach
    void setup() {
        myFileWriter = new MyTextFileWriter("testLogs.txt", true);
    }

    @Test
    void successfullyWriteToFile() {
        String expectedMessage = "testMessage";
        assertDoesNotThrow(() -> myFileWriter.writeToFile(expectedMessage, true));
    }

    @Test
    void failToWriteToFile() {
        String expectedMessage = "testMessage";
        String expectedInvalidFile = "";

        String expectedExceptionMessage;
        if (System.getProperty("os.name").equals("Windows 10")) {
            expectedExceptionMessage = expectedInvalidFile + " (The system cannot find the path specified)";
        } else {
            expectedExceptionMessage = expectedInvalidFile + " (No such file or directory)";
        }

        FileWriterException expectedException = new FileWriterException(expectedExceptionMessage, new IOException());

        myFileWriter.setFileToWrite(expectedInvalidFile);
        Exception actualException = assertThrows(FileWriterException.class, () -> myFileWriter.writeToFile(expectedMessage, true));

        assertThat(actualException.getMessage(), is(expectedException.getMessage()));
    }

    @Test
    void successfullyObtainLogPathWithConstructorArgs() {
        String expectedFilePath = "src/test/resources/testLogs.txt";
        assertThat(myFileWriter.getFileToWrite(), is(expectedFilePath));
    }

    @Test
    void successfullyObtainLogPathLogPathWithoutConstructorArgs() {
        String expectedFilePath = "src/main/resources/logs.txt";

        MyFileWriter myFileWriter = new MyTextFileWriter();
        assertThat(myFileWriter.getFileToWrite(), is(expectedFilePath));
    }
}