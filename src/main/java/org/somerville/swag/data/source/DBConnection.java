package org.somerville.swag.data.source;

import java.sql.Connection;

public interface DBConnection {
    Connection connect();
}
