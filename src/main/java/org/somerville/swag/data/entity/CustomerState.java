package org.somerville.swag.data.entity;

import javax.swing.*;

public interface CustomerState {
    void signUp(JPanel root, String[] guestData);
    void logIn(JPanel root, String email, String password);
    void logOut(JPanel root);
    void viewBasket();
    void addProductToBasket();
    void removeProductFromBasket();
    void purchaseItems();
}
