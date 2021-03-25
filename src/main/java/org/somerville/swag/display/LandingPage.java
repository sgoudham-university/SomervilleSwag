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
    private JLabel lblStatus;

    public LandingPage(JFrame oldFrame, Customer customer) {

        refreshState(customer);

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
                        "SwagUp Swag-no", JOptionPane.ERROR_MESSAGE);
            } else {
                new JFrameBuilder.Builder().buildDefaultJFrame("Sign Up", new SignUp(oldFrame, customer).root,true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });

        logInButton.addActionListener(actionEvent -> {
            if (customer.getCustomerState() instanceof LoggedIn) {
                JOptionPane.showMessageDialog(root, "D-money appreciates your effort to SwagIn Twice",
                        "SwagIn Swag-No", JOptionPane.ERROR_MESSAGE);
            } else {
                new JFrameBuilder.Builder().buildDefaultJFrame("Log In", new LogIn(oldFrame, customer).root, true);
                SwingUtilities.getWindowAncestor(root).dispose();
            }
        });

        logOutButton.addActionListener(e -> {
            customer.logOut(root);
            refreshState(customer);
        });

        viewBasketButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame("Your Basket", new Basket(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();

        });

        addToBasketButton.addActionListener(actionEvent -> {
            Product selectedProduct = listOfProducts.getSelectedValue();
            customer.addProductToBasket(root, selectedProduct, (int) quantitySpinner.getValue());
            showFrameWithProduct(selectedProduct);
        });
    }

    private List<Product> getAllProducts() {
        SQLiteSource sqLiteSource = new SQLiteSource();
        return sqLiteSource.getAllProductsInStock();
    }

    private void showFrameWithProduct(Product product) {
        SpinnerModel model = new SpinnerNumberModel(0, 0, product.getStockLevel() - 1, 1);
        JSpinner spinner = new JSpinner();
        JFormattedTextField spin = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        spin.setEditable(false);
        quantitySpinner.setModel(model);

        lblDesc.setText(product.getDescription());
        lblProductTitle.setText(product.getName());

        ImageIcon productImageIcon = new ImageIcon(product.getImagePath());
        Image scaledProductImage = productImageIcon.getImage().getScaledInstance(470, 390, Image.SCALE_SMOOTH);
        imageDisplay.setIcon(new ImageIcon(scaledProductImage));
    }

    private void displayProductList(List< Product> productMap, DefaultListModel<Product> listModel) {
        productMap.forEach(listModel::addElement);
        listOfProducts.setModel(listModel);
    }

    private void refreshState(Customer customer) {
        if (customer.getCustomerState() instanceof LoggedIn) {
            lblStatus.setText("Swagged In " + customer.getForename() + "!");
        } else {
            lblStatus.setText("Status: Not Swag");
        }
    }
}
