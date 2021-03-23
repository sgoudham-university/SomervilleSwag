package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Order;
import org.somerville.swag.data.entity.OrderLine;
import org.somerville.swag.data.entity.Product;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LandingPage {
    private JButton signUpButton;
    private JButton logInButton;
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

        initialiseFrame();

//        quantitySpinner.setBackground(new Color(9, 45, 71));
//        imagePanel.setBackground(new Color(9, 45, 71));
//        productPanel.setBackground(new Color(9, 45, 71));
//        listOfProducts.setBackground(new Color(9, 45, 71));
        HashMap<Integer, Product> allProducts = getAllProducts();
        displayProductList(allProducts, new DefaultListModel<>());

        listOfProducts.setSelectedValue(allProducts.get(0), true);
        lblProductTitle.setText(allProducts.get(0).getName());

        SpinnerModel model1 = new SpinnerNumberModel(1, 1, allProducts.get(0).getStockLevel(), 1);
        JSpinner spinner1 = new JSpinner();
        JFormattedTextField spin1 = ((JSpinner.DefaultEditor) spinner1.getEditor()).getTextField();
        spin1.setEditable(false);
        quantitySpinner.setModel(model1);

        lblDesc.setText(allProducts.get(0).getDescription());

        ImageIcon productImageIcon1 = new ImageIcon(allProducts.get(0).getImagepath());
        Image scaledProductImage1 = productImageIcon1.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH);
        imageDisplay.setIcon(new ImageIcon(scaledProductImage1));

        /**
         * Constructors, list and basic logic need moved
         * Only here for inital logic and UI checks
         * Remove ASAP
         * Do not leave comment once removed
         */

        listOfProducts.addListSelectionListener(actionEvent -> {
            Product selectedProduct = listOfProducts.getSelectedValue();

            SpinnerModel model = new SpinnerNumberModel(1, 1, selectedProduct.getStockLevel(), 1);
            JSpinner spinner = new JSpinner();
            JFormattedTextField spin = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
            spin.setEditable(false);
            quantitySpinner.setModel(model);

            lblDesc.setText(selectedProduct.getDescription());
            lblProductTitle.setText(selectedProduct.getName());

            ImageIcon productImageIcon = new ImageIcon(selectedProduct.getImagepath());
            Image scaledProductImage = productImageIcon.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH);
            imageDisplay.setIcon(new ImageIcon(scaledProductImage));

        });

        signUpButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Sign Up", new SignUp(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        logInButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Log In", new LogIn(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        viewBasketButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Your Basket", new Basket(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();

        });

        addToBasketButton.addActionListener(actionEvent -> customer.addProductToBasket());

        if(listOfProducts.getSelectedIndex() == -1){
            System.out.println("No product selected");
        } else {
            int quantity = (int) quantitySpinner.getValue();

            Product selectedProduct = listOfProducts.getSelectedValue();

            OrderLine orderLine = new OrderLine(selectedProduct, quantity);
            Order order = customer.getCurrentOrder();
            order.add(orderLine);
        }
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

    private void displayProductList(HashMap<Integer, Product> productMap, DefaultListModel<Product> listModel) {
        for (Map.Entry<Integer, Product> productEntry : productMap.entrySet()) {
            Product product = productEntry.getValue();
            if (product.getStockLevel() > 0 ){
                listModel.addElement(product);
            }
        }
        listOfProducts.setModel(listModel);
    }

    private void initialiseFrame() {
        HashMap<Integer, Product> allProducts = getAllProducts();
        displayProductList(allProducts, new DefaultListModel<>());

        listOfProducts.setSelectedValue(allProducts.get(0), true);
        lblProductTitle.setText(allProducts.get(0).getName());

        SpinnerModel model1 = new SpinnerNumberModel(1, 1, allProducts.get(0).getStockLevel(), 1);
        JSpinner spinner1 = new JSpinner();
        JFormattedTextField spin1 = ((JSpinner.DefaultEditor) spinner1.getEditor()).getTextField();
        spin1.setEditable(false);
        quantitySpinner.setModel(model1);

        lblDesc.setText(allProducts.get(0).getDescription());

        ImageIcon productImageIcon1 = new ImageIcon(allProducts.get(0).getImagepath());
        Image scaledProductImage1 = productImageIcon1.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH);
        imageDisplay.setIcon(new ImageIcon(scaledProductImage1));
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(new JFrame(), customer).root, true);
    }
}
