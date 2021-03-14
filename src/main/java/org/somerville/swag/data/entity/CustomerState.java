package org.somerville.swag.data.entity;

public interface CustomerState {
    void signUp();
    void logIn();
    void logOut();
    void viewBasket();
    void addProductToBasket();
    void removeProductFromBasket();
    void purchaseItems();
}
