package org.somerville.swag.data.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.somerville.swag.data.exception.FileWriterException;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class MyTextFileWriterTest {

    private MyFileWriter myFileWriter;

    @BeforeEach
    void setup() {
        myFileWriter = new MyTextFileWriter("testLogs.txt", true);
    }

    @Test
    void successfullyWriteToFileFake() throws FileWriterException {
        String expectedFirstMessage = "firstTestMessage";
        String expectedSecondMessage = "secondTestMessage";
        MyFakeTextFileWriter actualFakeTextFileWriter = new MyFakeTextFileWriter();

        actualFakeTextFileWriter.writeToFile(expectedFirstMessage, true);
        actualFakeTextFileWriter.writeToFile(expectedSecondMessage, true);
        List<String> actualFakeFile = actualFakeTextFileWriter.getFakeFile();

        assertThat(actualFakeFile.get(0), is(expectedFirstMessage));
        assertThat(actualFakeFile.get(1), is(expectedSecondMessage));
    }

    @Test
    void failToWriteToFileFake() throws FileWriterException {
        String expectedMessage = "testMessage";
        String expectedInvalidFile = "";
        String expectedExceptionMessage = "Failed to Write To File!";
        FileWriterException expectedException = new FileWriterException(expectedExceptionMessage, new IOException());
        MyFakeTextFileWriter actualFakeTextFileWriter = spy(new MyFakeTextFileWriter());

        doThrow(expectedException).when(actualFakeTextFileWriter).writeToFile(expectedMessage, true);

        actualFakeTextFileWriter.setFileToWrite(expectedInvalidFile);
        Throwable thrownException = assertThrows(FileWriterException.class, () -> actualFakeTextFileWriter.writeToFile(expectedMessage, true));

        assertThat(thrownException.getMessage(), is(expectedException.getMessage()));
        assertThat(actualFakeTextFileWriter.getFileToWrite(), is(expectedInvalidFile));
        assertThat(actualFakeTextFileWriter.getFakeFile(), is(empty()));
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
    void successfullyGetLogPathWithConstructorArgsFake() {
        String expectedFilePath = "src/test/resources/testLogs.txt";

        MyFakeTextFileWriter actualFakeFileWriter = new MyFakeTextFileWriter("testLogs.txt", true);
        String actualFilePath = actualFakeFileWriter.getFileToWrite();

        assertThat(actualFilePath, is(expectedFilePath));
    }

    @Test
    void successfullyGetLogPathWithoutConstructorArgs() {
        String expectedFilePath = "src/main/resources/logs.txt";

        MyFileWriter myFileWriter = new MyTextFileWriter();
        String actualFilePath = myFileWriter.getFileToWrite();

        assertThat(actualFilePath, is(expectedFilePath));
    }

    @Test
    void successfullyGetLogPathWithoutConstructorArgsFake() {
        String expectedFilePath = "src/main/resources/logs.txt";

        MyFakeTextFileWriter actualFakeFileWriter = new MyFakeTextFileWriter();
        String actualFilePath = actualFakeFileWriter.getFileToWrite();

        assertThat(actualFilePath, is(expectedFilePath));
    }
}