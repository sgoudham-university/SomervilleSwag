package org.somerville.swag.data.source;

import java.util.List;

public interface DataSource {
    public List<List<String>> getDataFromTable (String tableName, String[] columnNames);
}
