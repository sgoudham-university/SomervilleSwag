package org.somerville.swag.data.entity;

import org.somerville.swag.data.state.Guest;
import org.somerville.swag.data.state.LoggedIn;

public class Customer  {
    private String username;
    private String password;
    private CustomerState customerState;

    public Customer() {
        this.customerState = new Guest(this);
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.customerState = new LoggedIn(this);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
