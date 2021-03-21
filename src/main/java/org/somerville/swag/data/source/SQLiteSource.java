package org.somerville.swag.data.source;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.exception.SQLStatementException;

import java.util.List;

import static org.somerville.swag.data.source.util.Constants.GET_CUSTOMER_QUERY;

public class SQLiteSource implements DBSource {

    private DBExecute sqLiteExecute;

    public SQLiteSource() {
        sqLiteExecute = new SQLiteExecute(SQLiteConnection.getInstance());
    }

    @Override
    public Customer getCustomer(String email, String password) {
        String getCustomerQuery = GET_CUSTOMER_QUERY.replace("{email}", email).replace("{password}", password);
        try {
            sqLiteExecute.executeSelect(getCustomerQuery);
        } catch (SQLStatementException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertCustomer(Customer guest) {

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    public void setSqLiteExecute(DBExecute sqLiteExecute) {
        this.sqLiteExecute = sqLiteExecute;
    }
}
