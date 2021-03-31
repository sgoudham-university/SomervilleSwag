package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.somerville.swag.data.exception.FileWriterException;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

class MyTextFileWriterTest {

    MyFileWriter myFileWriter;

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

        String actualFilePath = myFileWriter.getFileToWrite();

        assertThat(actualFilePath, is(expectedFilePath));
    }

    @Test
    void successfullyGetLogPathWithoutConstructorArgs() {
        String expectedFilePath = "src/main/resources/logs.txt";

        MyFileWriter myFileWriter = new MyTextFileWriter();
        String actualFilePath = myFileWriter.getFileToWrite();

        assertThat(actualFilePath, is(expectedFilePath));
    }
}