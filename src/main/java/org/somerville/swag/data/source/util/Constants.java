package org.somerville.swag.data.source.util;

public class Constants {
    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String TEST_RESOURCES_PATH = "src/test/resources/";
    public static final String LOG_FILE = "logs";
    public static final String DOT_TEXT = ".txt";

    public static final String JDBC_URL = "jdbc:sqlite:";
    public static final String DATABASE_URL = RESOURCES_PATH + "database/SomervilleSwagDB.db";
    public static final String CREATE_TABLES_SCRIPT = RESOURCES_PATH + "database/CreateTables.sql";
    public static final String POPULATE_TABLES_SCRIPT = RESOURCES_PATH + "database/PopulateProductTable.sql";

    public static final String GET_CUSTOMER_QUERY = "SELECT * FROM CUSTOMER WHERE Email = {email} AND Password = {password};";
}
