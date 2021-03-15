package org.somerville.swag.data.exception;

public class SQLConnectionException extends Exception {
    public SQLConnectionException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
