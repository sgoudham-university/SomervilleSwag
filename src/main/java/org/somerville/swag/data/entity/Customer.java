package org.somerville.swag.data.entity;

import org.somerville.swag.data.entity.state.Guest;
import org.somerville.swag.data.entity.state.LoggedIn;

public class Customer {
    private String email;
    private String password;
    private CustomerState customerState;
    private Order currentOrder;
    private int customerId;
    private String forename;
    private String surname;
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
        this.email = email;
        this.password = password;
        this.customerState = new LoggedIn(this);
        this.currentOrder = order;

    }

    public Customer(String email, String password, Order currentOrder, int customerId, String forename,
                    String surname, String addressLine1, String addressLine2, String city, String postcode,
                    String phoneNumber) {

        this.customerState = new LoggedIn(this);

        this.email = email;
        this.password = password;
        this.currentOrder = currentOrder;
        this.customerId = customerId;
        this.forename = forename;
        this.surname = surname;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
    }

    public void changeCustomerState(CustomerState customerState) {
        this.customerState = customerState;
    }

    public CustomerState getCustomerState() {
        return customerState;
    }

    public void signUp() {
        customerState.signUp();
    }

    public void logIn() {
        customerState.logIn();
    }

    public void logOut() {
        customerState.logOut();
    }

    public void viewBasket() {
        customerState.viewBasket();
    }

    public void addProductToBasket() {
        customerState.addProductToBasket();
    }

    public void removeProductFromBasket() {
        customerState.removeProductFromBasket();
    }

    public void purchaseItems() {
        customerState.purchaseItems();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
