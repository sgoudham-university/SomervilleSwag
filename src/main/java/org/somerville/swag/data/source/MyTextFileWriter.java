package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.FileWriterException;

import java.io.FileWriter;
import java.io.IOException;

import static org.somerville.swag.data.source.util.Constants.*;

public class MyTextFileWriter implements MyFileWriter {

    private String fileToWrite;

    public MyTextFileWriter() {
        this.fileToWrite = RESOURCES_PATH + LOG_FILE + DOT_TEXT;
    }

    public MyTextFileWriter(String fileToWrite, boolean test) {
        String path = test ? TEST_RESOURCES_PATH : RESOURCES_PATH;
        this.fileToWrite = path + fileToWrite;
    }

    @Override
    public void writeToFile(String message, boolean append) throws FileWriterException {
        try (FileWriter fileWriter = new FileWriter(fileToWrite, append)) {
            fileWriter.write(message + "\n");
            fileWriter.flush();
        } catch (IOException ioe) {
            throw new FileWriterException(ioe.getMessage(), ioe);
        }
    }

    @Override
    public void setFileToWrite(String fileToWrite) {
        this.fileToWrite = fileToWrite;
    }

    @Override
    public String getFileToWrite() {
        return fileToWrite;
    }
}
