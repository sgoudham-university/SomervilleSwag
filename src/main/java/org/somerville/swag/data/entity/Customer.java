package org.somerville.swag.data.entity;

import org.somerville.swag.data.state.Guest;
import org.somerville.swag.data.state.LoggedIn;

public class Customer  {
    private String email;
    private String password;
    private CustomerState customerState;
    private Order currentOrder;
    private int CustomerId;



    public Customer() {
        this.customerState = new Guest(this);
        this.currentOrder = new Order();
    }

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
        this.customerState = new LoggedIn(this);

    }
    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }
    public void changeCustomerState(CustomerState customerState) {
        this.customerState = customerState;
    }

    public CustomerState getCustomerState() {
        return customerState;
    }

    public void signUp(){
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
}
