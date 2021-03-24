package org.somerville.swag.data.entity;

import javax.swing.*;
import java.util.List;

public interface CustomerState {
    void signUp(JPanel root, List<String> guestData);
    void logIn(JPanel root, String email, String password);
    void logOut(JPanel root);
    void viewBasket();
    void addProductToBasket(Product product, int quantity);
    void removeProductFromBasket(OrderLine orderLine);
    void purchaseItems(JPanel root);
}
