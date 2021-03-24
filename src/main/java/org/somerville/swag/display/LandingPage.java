package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.source.DBPopulate;
import org.somerville.swag.data.source.SQLiteConnection;
import org.somerville.swag.data.source.SQLiteSource;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

        List<Product> allProducts = getAllProducts();
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

        logOutButton.addActionListener(e -> {
            customer.logOut(root);
        });

        viewBasketButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Your Basket", new Basket(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();

        });

        addToBasketButton.addActionListener(actionEvent -> customer.addProductToBasket());
    }

    private List<Product> getAllProducts() {
        SQLiteSource sqlSoure = new SQLiteSource();
        return sqlSoure.getAllProductsInStock();
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

    private void displayProductList(List< Product> productMap, DefaultListModel<Product> listModel) {
        productMap.forEach(product -> listModel.addElement(product));
        listOfProducts.setModel(listModel);
    }

    public static void main(String[] args) {
        DBPopulate dbPopulate = new DBPopulate(SQLiteConnection.getInstance());
        dbPopulate.createTables();
        dbPopulate.populateProductTable();

        Customer customer = new Customer();
        new JFrameBuilder.Builder().buildDefaultJFrame("Somerville Swag", new LandingPage(new JFrame(), customer).root, true);
    }
}
