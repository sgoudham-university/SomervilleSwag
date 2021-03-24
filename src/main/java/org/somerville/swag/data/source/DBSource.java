package org.somerville.swag.data.source;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;

import java.util.List;

public interface DBSource {
    Customer getCustomer(String email, String password, Customer customer);
    void insertCustomer(List<String> guestData);
    List<Product> getAllProductsInStock();
    boolean ifCustomerExists(String email, String password);
}
