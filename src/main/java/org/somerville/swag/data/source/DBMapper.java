package org.somerville.swag.data.source;

import org.somerville.swag.data.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMapper {

    public DBMapper() { }

    public Customer mapToCustomer(ResultSet customerData, Customer customer) {
        try {
            if (customerData.isBeforeFirst()) {
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
            }
        } catch (SQLException sqle) {
            // Add logging service
        }
        return customer;
    }
}
