package org.somerville.swag.data.source;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.exception.SQLStatementException;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.somerville.swag.data.source.util.Constants.GET_CUSTOMER_QUERY;

public class SQLiteSource implements DBSource {

    private DBExecute dbExecute;
    private DBMapper dbMapper;

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public SQLiteSource() {
        dbExecute = new SQLiteExecute(SQLiteConnection.getInstance());
        dbMapper = new DBMapper();
    }

    @Override
    public Customer getCustomer(String email, String password, Customer customer) {
        Customer newCustomer = null;
        String getCustomerQuery = GET_CUSTOMER_QUERY.replace("{email}", email).replace("{password}", password);

        try (ResultSet customerData = dbExecute.executeSelect(getCustomerQuery)) {
            newCustomer = dbMapper.mapToCustomer(customerData, customer);
            loggingService.logDatabaseGetCustomerSuccess(newCustomer.getCustomerId());
        } catch (SQLStatementException | SQLException err) {
            loggingService.logDatabaseGetCustomerFailure(getCustomerQuery, err.getMessage());
        }

        return newCustomer;
    }

    @Override
    public void insertCustomer(List<String> guestData) {
        String insertCustomerStatement = " ";
        try {
            dbExecute.executeInsert(insertCustomerStatement);
            loggingService.logDatabaseInsertCustomerSuccess(insertCustomerStatement);
        } catch (SQLStatementException sse) {
            loggingService.logDatabaseInsertCustomerFailure(insertCustomerStatement, sse.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {

        return null;
    }

    public void setDbExecute(DBExecute dbExecute) {
        this.dbExecute = dbExecute;
    }

    public void setDbMapper(DBMapper dbMapper) {
        this.dbMapper = dbMapper;
    }
}
