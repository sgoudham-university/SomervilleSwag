package org.somerville.swag.data.entity;

import javax.swing.*;

public interface CustomerState {
    void signUp(JPanel root, String[] guestData);
    void logIn();
    void logOut();
    void viewBasket();
    void addProductToBasket();
    void removeProductFromBasket();
    void purchaseItems();
}
