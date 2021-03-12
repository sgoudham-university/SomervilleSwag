package org.somerville.swag.data.exception;

public class DatabaseException extends Exception {
    public DatabaseException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
