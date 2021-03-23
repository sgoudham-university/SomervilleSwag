package org.somerville.swag.data.source;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.service.LoggingService;
import org.somerville.swag.data.service.LoggingServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBMapper {

    private LoggingService loggingService = LoggingServiceImpl.getInstance();

    public DBMapper() { }

    public void mapToCustomer(ResultSet customerData, Customer customer) {
        try {
            if (!customerData.isBeforeFirst()) {
                loggingService.logDatabaseCustomerNotFound();
            } else {
                while (customerData.next()) {
                    customer.setCustomerId(customerData.getInt("CustomerId"));
                    customer.setForename(customerData.getString("Forename"));
                    customer.setSurname(customerData.getString("Surname"));
                    customer.setEmail(customerData.getString("Email"));
                    customer.setPassword(customerData.getString("Password"));
                    customer.setAddressLine1(customerData.getString("AddressLine1"));
                    customer.setAddressLine2(customerData.getString("AddressLine2"));
                    customer.setCity(customerData.getString("City"));
                    customer.setPostcode(customerData.getString("Postcode"));
                    customer.setPhoneNumber(customerData.getString("PhoneNumber"));
                }
                loggingService.logDatabaseCustomerMapSuccess(customer.getCustomerId());
            }
        } catch (SQLException sqle) {
            loggingService.logDatabaseCustomerMapFailure(sqle.getMessage());
        }
    }

    public void mapToProducts(ResultSet allProductsData, List<Product> allProducts) {
        try {
            if (!allProductsData.isBeforeFirst()) {
                loggingService.logDatabaseNoProductsInStock();
            } else {
                while (allProductsData.next()) {
                    allProducts.add(new Product(
                            allProductsData.getInt("ProductId"),
                            allProductsData.getString("Name"),
                            allProductsData.getString("Description"),
                            allProductsData.getBigDecimal("Price"),
                            allProductsData.getInt("StockLevel"),
                            allProductsData.getString("ImagePath")
                    ));
                }
                loggingService.logDatabaseAllProductsMapSuccess();
            }
        } catch (SQLException sqle) {
            loggingService.logDatabaseAllProductsMapFailure(sqle.getMessage());
        }
    }

    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }
}
