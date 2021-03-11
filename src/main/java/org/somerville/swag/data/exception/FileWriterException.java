package org.somerville.swag.data.exception;

public class FileWriterException extends Exception {
    public FileWriterException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
