package org.somerville.swag.display;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.entity.Order;
import org.somerville.swag.data.entity.OrderLine;
import org.somerville.swag.data.entity.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LandingPage {
    private JButton signUpButton;
    private JButton logInButton;
    private JButton viewBasketButton;
    private JButton addToBasketButton;
    public JPanel root;
    private JList listOfProducts;
    private JPanel imagePanel;
    private JSpinner quantitySpinner;
    private JLabel lblDesc;

    public LandingPage(JFrame oldFrame, Customer customer) {

        /**
         * Constructors, list and basic logic need moved
         * Only here for inital logic and UI checks
         * Remove ASAP
         * Do not leave comment once removed
         */
        DefaultListModel listModel = new DefaultListModel();

        Product productA = new Product(0,"Product A","Description A", BigDecimal.valueOf(19.99), 5, "Path");
        Product productB = new Product(0,"Product B","Description B", BigDecimal.valueOf(29.99), 10, "Path");

        //-------------------- BEGIN LIST CREATION----------------------------------------------------------------------

        HashMap<Integer, Product> productMap = new HashMap<>(); //set this to the DB read fucntion
        productMap.put(0, productA);
        productMap.put(1, productB);

        for (Map.Entry<Integer, Product> productEntry : productMap.entrySet()) {
            Product product = productEntry.getValue();
            if (product.getStockLevel() > 0 ){
                listModel.addElement(product);
            }
        }
        listOfProducts.setModel(listModel);

        //---------------------END OF LIST CREATION--------------------------------------------------------------------

    // -------------------- BEGIN DYNAMIC DISPLAY BLOCK ----------------------------------------------------------------
        listOfProducts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object selectedObject = (Object) listOfProducts.getSelectedValue();
                Product selectedProduct = (Product) selectedObject;

                SpinnerModel model = new SpinnerNumberModel(1,1,selectedProduct.getStockLevel(),1);
                JSpinner spinner= new JSpinner();
                JFormattedTextField spin=((JSpinner.DefaultEditor)spinner.getEditor()).getTextField();
                spin.setEditable(false);
                quantitySpinner.setModel(model);

                lblDesc.setText(selectedProduct.getDescription());
            }
        });
    // -------------------- BEGIN DYNAMIC DISPLAY BLOCK ----------------------------------------------------------------

        signUpButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new SignUp(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        logInButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new LogIn(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        viewBasketButton.addActionListener(actionEvent -> {
            new JFrameBuilder.Builder().buildDefaultJFrame(new Basket(oldFrame, customer).root,true);
            SwingUtilities.getWindowAncestor(root).dispose();
        });

        addToBasketButton.addActionListener(actionEvent -> customer.addProductToBasket());
        if(listOfProducts.getSelectedIndex() == -1){
            System.out.println("No product selected");
        } else {
            int quantity = (int) quantitySpinner.getValue();

            Object selectedObject = (Object)listOfProducts.getSelectedValue();
            Product selectedProduct = (Product)selectedObject;

            OrderLine orderLine = new OrderLine(selectedProduct, quantity);
            Order order = customer.getCurrentOrder();
            order.addOrderLine(orderLine);
        }
    }



    public static void main(String[] args) {
        Customer customer = new Customer();
        new JFrameBuilder.Builder().buildDefaultJFrame(new LandingPage(new JFrame(), customer).root, true);
    }
}
