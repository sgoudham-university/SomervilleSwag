package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Product;
import org.somerville.swag.data.entity.state.LoggedIn;
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
            if (customer.getCustomerState() instanceof LoggedIn) {
                JOptionPane.showMessageDialog(root, "Uh Oh! Can't Sign Up When Logged In!",
                        "Sign Up Swag-no", JOptionPane.ERROR_MESSAGE);
            } else {
                new JFrameBuilder.Builder().buildDefaultJFrame("Sign Up", new SignUp(oldFrame, customer).root,true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
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

        addToBasketButton.addActionListener(actionEvent -> {
            Product selectedProduct = listOfProducts.getSelectedValue();
            customer.addProductToBasket(selectedProduct, (int) quantitySpinner.getValue());
            showFrameWithProduct(selectedProduct);
        });
    }

    private List<Product> getAllProducts() {
        SQLiteSource sqLiteSource = new SQLiteSource();
        return sqLiteSource.getAllProductsInStock();
    }

    private void showFrameWithProduct(Product product) {
        SpinnerModel model = new SpinnerNumberModel(0, 0, product.getStockLevel(), 1);
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
        productMap.forEach(listModel::addElement);
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
