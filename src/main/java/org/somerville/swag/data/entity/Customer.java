package org.somerville.swag.data.entity;

import org.somerville.swag.data.entity.state.Guest;
import org.somerville.swag.data.entity.state.LoggedIn;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class Customer {
    private CustomerState customerState;
    private Order currentOrder;
    private int customerId;
    private String forename;
    private String surname;
    private String email;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postcode;
    private String phoneNumber;


    public Customer() {
        this.customerState = new Guest(this);
        this.currentOrder = new Order();
    }

    public Customer(String email, String password, Order order) {
        this.customerState = new LoggedIn(this);
        this.email = email;
        this.password = password;
        this.currentOrder = order;
    }

    public Customer(Order currentOrder, int customerId, String forename, String surname,
                    String email, String password, String addressLine1, String addressLine2,
                    String city, String postcode, String phoneNumber) {
        this.customerState = new LoggedIn(this);
        this.currentOrder = currentOrder;
        this.customerId = customerId;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
    }

    public void changeCustomerState(CustomerState customerState) {
        this.customerState = customerState;
    }

    public void signUp(JFrame oldFrame, JPanel root, List<String> guestData) {
        customerState.signUp(oldFrame, root, guestData);
    }

    public void logIn(JPanel root, String email, String password) {
        customerState.logIn(root, email, password);
    }

    public void logOut(JPanel root) {
        customerState.logOut(root);
    }

    public void viewBasket() {
        customerState.viewBasket();
    }

    public void addProductToBasket(Product product, int quantity) {
        customerState.addProductToBasket(product, quantity);
    }

    public void removeProductFromBasket(OrderLine orderLine) {
        customerState.removeProductFromBasket(orderLine);
    }

    public void purchaseItems(JPanel root) {
        customerState.purchaseItems(root);
    }

    public void refresh() {
        this.currentOrder = new Order();
        this.customerId = 0;
        this.forename = null;
        this.surname = null;
        this.email = null;
        this.password = null;
        this.addressLine1 = null;
        this.addressLine2 = null;
        this.city = null;
        this.postcode = null;
        this.phoneNumber = null;
    }

    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setForename(String forename) { this.forename = forename; }
    public void setCurrentOrder(Order order) { this.currentOrder = order; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
    public void setCity(String city) { this.city = city; }
    public void setPostcode(String postcode) { this.postcode = postcode; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public CustomerState getCustomerState() { return customerState; }
    public Order getCurrentOrder() {return currentOrder; }
    public int getCustomerId() { return customerId; }
    public String getForename() { return forename; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getAddressLine1() { return addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public String getCity() { return city; }
    public String getPostcode() { return postcode; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(currentOrder, customer.currentOrder) && Objects.equals(forename, customer.forename) && Objects.equals(surname, customer.surname) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password) && Objects.equals(addressLine1, customer.addressLine1) && Objects.equals(addressLine2, customer.addressLine2) && Objects.equals(city, customer.city) && Objects.equals(postcode, customer.postcode) && Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentOrder, customerId, forename, surname, email, password, addressLine1, addressLine2, city, postcode, phoneNumber);
    }
}

