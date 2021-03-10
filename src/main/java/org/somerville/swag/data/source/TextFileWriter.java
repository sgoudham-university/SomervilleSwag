package org.somerville.swag.data.source;

import org.somerville.swag.data.exeption.FileWriterException;

import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {

    private static final String RESOURCE = "src/main/resources/";
    private String fileToWrite;

    public TextFileWriter(String fileToWrite) {
        this.fileToWrite = RESOURCE + fileToWrite;
    }

    public void writeToFile(String message, boolean append) throws FileWriterException {
        try (FileWriter fileWriter = new FileWriter(fileToWrite, append)) {
            fileWriter.write(message + "\n");
            fileWriter.flush();
        } catch (IOException ioe) {
            throw new FileWriterException(ioe.getMessage(), ioe);
        }
    }

    public void setFileToWrite(String fileToWrite) {
        this.fileToWrite = fileToWrite;
    }
}
