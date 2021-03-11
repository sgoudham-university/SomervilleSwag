package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.FileWriterException;

public interface MyFileWriter {
    void writeToFile(String message, boolean append) throws FileWriterException;
    void setFileToWrite(String fileToWrite);
    String getFileToWrite();
}
