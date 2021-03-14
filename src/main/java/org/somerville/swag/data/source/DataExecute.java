package org.somerville.swag.data.source;

import java.sql.ResultSet;

public interface DataExecute {

    public ResultSet executeSelect(String selectQuery);
}
