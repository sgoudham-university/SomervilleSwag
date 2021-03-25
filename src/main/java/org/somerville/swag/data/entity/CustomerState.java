package org.somerville.swag.data.entity;

import javax.swing.*;
import java.util.List;

public interface CustomerState {
    void signUp(JPanel root, JFrame oldFrame, List<String> guestData);
    void logIn(JPanel root, String email, String password);
    void logOut(JPanel root);
    void addProductToBasket(JPanel root, Product product, int quantity);
    void removeProductFromBasket(JPanel root, OrderLine orderLine);
    void purchaseProducts(JFrame oldFrame, JPanel root, String txtCardNo, String txtCvv);
}
