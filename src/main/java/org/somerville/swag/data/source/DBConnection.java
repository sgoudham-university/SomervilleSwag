package org.somerville.swag.data.source;

import org.somerville.swag.data.exception.DatabaseException;

import java.sql.Connection;

public interface DBConnection {
    Connection connect(String databaseUrl) throws DatabaseException;
    Connection connect() throws DatabaseException;
}
