package org.somerville.swag.data.exeption;

public class FileWriterException extends Exception {
    public FileWriterException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
