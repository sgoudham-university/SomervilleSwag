package org.somerville.swag.data.exception;

public class SQLStatementException extends Exception {
    public SQLStatementException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
