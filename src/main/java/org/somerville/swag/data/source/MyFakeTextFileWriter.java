package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.FileWriterException;

import java.util.ArrayList;
import java.util.List;

import static org.somerville.swag.data.source.util.Constants.*;

public class MyFakeTextFileWriter implements MyFileWriter {

    private String fileToWrite;
    private final List<String> fakeFile = new ArrayList<>();

    public MyFakeTextFileWriter() {
        this.fileToWrite = RESOURCES_PATH + LOG_FILE + DOT_TEXT;
    }

    public MyFakeTextFileWriter(String fileToWrite, boolean test) {
        String path = test ? TEST_RESOURCES_PATH : RESOURCES_PATH;
        this.fileToWrite = path + fileToWrite;
    }

    @Override
    public void writeToFile(String message, boolean append) throws FileWriterException {
        fakeFile.add(message);
    }

    @Override
    public void setFileToWrite(String fileToWrite) {
        this.fileToWrite = fileToWrite;
    }

    @Override
    public String getFileToWrite() {
        return fileToWrite;
    }

    public List<String> getFakeFile() {
        return fakeFile;
    }
}
