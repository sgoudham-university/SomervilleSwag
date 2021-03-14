package org.somerville.swag.data.domain;

public class Events {

    private static final String DATABASE_WRITE_SUCCESS_SUFFIX = ".database_write_success";
    private static final String DATABASE_WRITE_FAILURE_SUFFIX = ".database_write_failure";
    private static final String DATABASE_READ_SUCCESS_SUFFIX = ".database_read_success";
    private static final String DATABASE_READ_FAILURE_SUFFIX = ".database_read_failure";
    private static final String DATABASE_CONNECT_SUCCESS_SUFFIX = ".database_connect_success";
    private static final String DATABASE_CONNECT_FAILURE_SUFFIX = ".database_connect_failure";

    public String getProjectPrefix() {
        return "org.somerville.swag";
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

    public String getDatabaseConnectSuccess() { return getProjectPrefix() + DATABASE_CONNECT_SUCCESS_SUFFIX; }

    public String getDatabaseConnectFailure() { return getProjectPrefix() + DATABASE_CONNECT_FAILURE_SUFFIX;}
}
