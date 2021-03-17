package org.somerville.swag.data.domain;

public class Events {

    private static final String DATABASE_CONNECT_SUCCESS_SUFFIX = ".database_connect_success";
    private static final String DATABASE_CONNECT_FAILURE_SUFFIX = ".database_connect_failure";
    private static final String DATABASE_CREATE_TABLES_SUCCESS_SUFFIX = ".database_create_tables_success";
    private static final String DATABASE_CREATE_TABLES_FAILURE_SUFFIX = ".database_create_tables_failure";
    private static final String DATABASE_POPULATE_TABLES_SUCCESS_SUFFIX = ".database_populate_tables_success";
    private static final String DATABASE_POPULATE_TABLES_FAILURE_SUFFIX = ".database_populate_tables_failure";
    private static final String DATABASE_WRITE_SUCCESS_SUFFIX = ".database_write_success";
    private static final String DATABASE_WRITE_FAILURE_SUFFIX = ".database_write_failure";
    private static final String DATABASE_READ_SUCCESS_SUFFIX = ".database_read_success";
    private static final String DATABASE_READ_FAILURE_SUFFIX = ".database_read_failure";



    public String getProjectPrefix() {
        return "org.somerville.swag";
    }
    public String getDatabaseConnectSuccess() { return getProjectPrefix() + DATABASE_CONNECT_SUCCESS_SUFFIX; }
    public String getDatabaseConnectFailure() { return getProjectPrefix() + DATABASE_CONNECT_FAILURE_SUFFIX;}

    public String getDatabaseCreateTablesSuccessSuffix() {
        return getProjectPrefix() + DATABASE_CREATE_TABLES_SUCCESS_SUFFIX;
    }
    public String getDatabaseCreateTablesFailureSuffix() {
        return getProjectPrefix() + DATABASE_CREATE_TABLES_FAILURE_SUFFIX;
    }

    public String getDatabasePopulateTablesSuccessSuffix() {
        return getProjectPrefix() + DATABASE_POPULATE_TABLES_SUCCESS_SUFFIX;
    }

    public String getDatabasePopulateTablesFailureSuffix() {
        return getProjectPrefix() + DATABASE_POPULATE_TABLES_FAILURE_SUFFIX;
    }

    public String getDatabaseWriteSuccess() {
        return getProjectPrefix() + DATABASE_WRITE_SUCCESS_SUFFIX;
    }

    public String getDatabaseWriteFailure() {
        return getProjectPrefix() + DATABASE_WRITE_FAILURE_SUFFIX;
    }

    public String getDatabaseReadSuccess() {
        return getProjectPrefix() + DATABASE_READ_SUCCESS_SUFFIX;
    }

    public String getDatabaseReadFailure() {
        return getProjectPrefix() + DATABASE_READ_FAILURE_SUFFIX;
    }


}
