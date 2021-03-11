package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.somerville.swag.data.exception.FileWriterException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class MyTextFileWriterTest {

    // Writing Tests mixing Mocks and Normal Implementations to Demonstrate Knowledge and Expertise

    private static MyFileWriter mockedMyFileWriter;
    private static MyFileWriter myFileWriter;

    @BeforeAll
    static void init() {
        mockedMyFileWriter = mock(MyTextFileWriter.class);
        myFileWriter = new MyTextFileWriter("testLogs.txt", true);
    }

    @Test
    void shouldSuccessfullyWriteToFile() {
        String expectedMessage = "testMessage";
        assertDoesNotThrow(() -> myFileWriter.writeToFile(expectedMessage, true));
    }

    @Test
    void shouldSuccessfullyWriteToFileMocked() {
        String expectedMessage = "testMessage";
        assertDoesNotThrow(() -> mockedMyFileWriter.writeToFile(expectedMessage, true));
    }

    @Test
    void shouldFailToWriteToFileMocked() throws FileWriterException {
        String expectedMessage = "testMessage";

        String expectedErrorMessage = "FAILURE!";
        FileWriterException expectedException = new FileWriterException(expectedErrorMessage, new IOException());

        doThrow(expectedException).when(mockedMyFileWriter).writeToFile(expectedMessage, true);

        Exception actualException = assertThrows(FileWriterException.class, () -> mockedMyFileWriter.writeToFile(expectedMessage, true));
        assertThat(actualException.getMessage(), is(expectedErrorMessage));
    }

    @Test
    void shouldObtainCorrectLogPathWithConstructorArgs() {
        String expectedFilePath = "src/test/resources/testLogs.txt";
        assertThat(myFileWriter.getFileToWrite(), is(expectedFilePath));
    }

    @Test
    void shouldObtainCorrectLogPathWithoutConstructorArgs() {
        String expectedFilePath = "src/main/resources/logs.txt";
        MyFileWriter myFileWriter = new MyTextFileWriter();

        assertThat(myFileWriter.getFileToWrite(), is(expectedFilePath));
    }
}