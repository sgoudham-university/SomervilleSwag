package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;

public class LandingPage {
    private JButton signUpButton;
    private JButton logInButton;
    private JButton logOutButton;
    private JButton viewBasketButton;
    private JButton addToBasketButton;
    public JPanel root;
    private JList<Product> listOfProducts;
    private JPanel imagePanel;
    private JSpinner quantitySpinner;
    private JLabel lblDesc;
    private JLabel imageDisplay;
    private JLabel lblTitle;
    private JPanel productPanel;
    private JLabel lblProductTitle;

    public LandingPage(JFrame oldFrame, Customer customer) {

        HashMap<Integer, Product> allProducts = getAllProducts();
        showFrameWithProduct(allProducts.get(0));
        displayProductList(allProducts, new DefaultListModel<>());
        listOfProducts.setSelectedValue(allProducts.get(0), true);

        listOfProducts.addListSelectionListener(actionEvent -> {
            Product selectedProduct = listOfProducts.getSelectedValue();
            showFrameWithProduct(selectedProduct);
        });

        signUpButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Sign Up", new SignUp(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        logInButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Log In", new LogIn(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        logOutButton.addActionListener(e -> customer.logOut());

        viewBasketButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Your Basket", new Basket(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();

        });

        addToBasketButton.addActionListener(actionEvent -> customer.addProductToBasket());
    }

    private HashMap<Integer, Product> getAllProducts() {
        Product productA = new Product(0,"Product A","Description C", BigDecimal.valueOf(19.99), 5, "src/main/resources/product.images/derek_crocs.png");
        Product productB = new Product(0,"Product B","Description B", BigDecimal.valueOf(29.99), 10, "src/main/resources/product.images/derek_snowglobe.png");
        Product productC = new Product(0,"Product C","Description B", BigDecimal.valueOf(29.99), 10, "src/main/resources/product.images/derek_snowglobe.png");

        HashMap<Integer, Product> productMap = new HashMap<>(); //set this to the DB read function
        productMap.put(0, productA);
        productMap.put(1, productB);
        productMap.put(2, productC);
        return productMap;
    }

    private void showFrameWithProduct(Product product) {
        SpinnerModel model = new SpinnerNumberModel(1, 1, product.getStockLevel(), 1);
        JSpinner spinner = new JSpinner();
        JFormattedTextField spin = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        spin.setEditable(false);
        quantitySpinner.setModel(model);

        lblDesc.setText(product.getDescription());
        lblProductTitle.setText(product.getName());

        ImageIcon productImageIcon1 = new ImageIcon(product.getImagePath());
        Image scaledProductImage1 = productImageIcon1.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH);
        imageDisplay.setIcon(new ImageIcon(scaledProductImage1));
    }

    private void displayProductList(HashMap<Integer, Product> productMap, DefaultListModel<Product> listModel) {
        productMap.forEach((id, product) -> listModel.addElement(product));
        listOfProducts.setModel(listModel);
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(new JFrame(), customer).root, true);
    }
}
