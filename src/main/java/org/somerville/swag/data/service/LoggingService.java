package org.somerville.swag.data.service;

import org.slf4j.Logger;
import org.somerville.swag.data.service.util.Clock;
import org.somerville.swag.data.source.MyFileWriter;

public interface LoggingService {
    void logDatabaseConnectSuccess(String databaseUrl);
    void logDatabaseConnectFailure(String databaseUrl, String failureMessage);
    void logDatabaseCreateTablesSuccess(String fileName);
    void logDatabaseCreateTablesFailure(String fileName, String failureMessage);
    void logDatabasePopulateProductTableSuccess(String fileName);
    void logDatabasePopulateProductTableFailure(String fileName, String failureMessage);
    void logDatabaseInsertSuccess(String insertStatement, int rowsUpdated);
    void logDatabaseInsertFailure(String insertStatement, String failureMessage);
    void logDatabaseSelectSuccess(String selectQuery);
    void logDatabaseSelectFailure(String selectQuery, String failureMessage);

    void logDatabaseGetCustomerSuccess(int customerId);
    void logDatabaseGetCustomerFailure(String selectQuery, String failureMessage);
    void logDatabaseInsertCustomerSuccess(String insertStatement);
    void logDatabaseInsertCustomerFailure(String insertStatement, String failureMessage);
    void logDatabaseCustomerNotFound(String email);
    void logDatabaseCustomerAlreadyExists();
    void logCustomerSignedUp(String email);
    void logCustomerLoggedIn(int customerId);
    void logCustomerLoggedOut(int customerId);
    void logCustomerAddProductToBasket(int customerId, int productId);
    void logCustomerCheckout(int customerId, int orderId);
    void logDatabaseCustomerMapSuccess(int customerId);
    void logDatabaseCustomerMapFailure(String failureMessage);

    void logDatabaseGetAllProductsInStockSuccess();
    void logDatabaseGetAllProductsInStockFailure(String getAllProductsQuery, String failureMessage);
    void logDatabaseNoProductsInStock();
    void logDatabaseAllProductsMapSuccess();
    void logDatabaseAllProductsMapFailure(String failureMessage);

    String retrieveLogMessage(String logMessage);
    void setClock(Clock clock);
    void setLogger(Logger logger);
    void setTextFileWriter(MyFileWriter textFileWriter);
    void writeLog(String logMessage);
}
