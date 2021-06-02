package org.somerville.swag.engine;

import org.somerville.swag.data.entity.Customer;
import org.somerville.swag.data.source.DBPopulate;
import org.somerville.swag.data.source.SQLiteConnection;
import org.somerville.swag.display.JFrameBuilder;
import org.somerville.swag.display.LandingPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DBPopulate dbPopulate = new DBPopulate(SQLiteConnection.getInstance());
        Customer customer = new Customer();

        dbPopulate.createTables();
        dbPopulate.populateProductTable();

        new JFrameBuilder.Builder().buildDefaultJFrame("\uD83D\uDE0E✨ Somerville Swag ✨\uD83D\uDE0E", new LandingPage(new JFrame(), customer).root, true);
    }
}
