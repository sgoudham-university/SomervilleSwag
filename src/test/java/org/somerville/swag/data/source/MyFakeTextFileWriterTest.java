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

class MyFakeTextFileWriterTest {

    MyFakeTextFileWriter myFakeTextFileWriter;

    @BeforeEach
    void setup() {
        myFakeTextFileWriter = spy(new MyFakeTextFileWriter("testLogs.txt", true));
    }

    @Test
    void successfullyWriteToFileFake() throws FileWriterException {
        String expectedFirstMessage = "firstTestMessage";
        String expectedSecondMessage = "secondTestMessage";

        myFakeTextFileWriter.writeToFile(expectedFirstMessage, true);
        myFakeTextFileWriter.writeToFile(expectedSecondMessage, true);
        List<String> actualFakeFile = myFakeTextFileWriter.getFakeFile();

        assertThat(actualFakeFile.get(0), is(expectedFirstMessage));
        assertThat(actualFakeFile.get(1), is(expectedSecondMessage));
    }

    @Test
    void failToWriteToFileFake() throws FileWriterException {
        String expectedMessage = "testMessage";
        String expectedInvalidFile = "";
        String expectedExceptionMessage = "Failed to Write To File!";
        FileWriterException expectedException = new FileWriterException(expectedExceptionMessage, new IOException());

        doThrow(expectedException).when(myFakeTextFileWriter).writeToFile(expectedMessage, true);

        myFakeTextFileWriter.setFileToWrite(expectedInvalidFile);
        Throwable thrownException = assertThrows(FileWriterException.class, () -> myFakeTextFileWriter.writeToFile(expectedMessage, true));

        assertThat(thrownException.getMessage(), is(expectedException.getMessage()));
        assertThat(myFakeTextFileWriter.getFileToWrite(), is(expectedInvalidFile));
        assertThat(myFakeTextFileWriter.getFakeFile(), is(empty()));
    }

    @Test
    void successfullyGetLogPathWithConstructorArgsFake() {
        String expectedFilePath = "src/test/resources/testLogs.txt";

        String actualFilePath = myFakeTextFileWriter.getFileToWrite();

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