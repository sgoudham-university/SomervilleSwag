package org.somerville.swag.display;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entities.Customer;
import org.somerville.swag.data.entities.Order;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class JFrameBuilderTest {

    static JFrame expectedJFrame;
    static String expectedTitle;
    static Container expectedContentPane;
    static int expectedDefaultCloseOperation;
    static boolean expectedVisibility;
    static Component expectedRelativeLocation;

    @BeforeAll
    static void init() {
        JFrame oldframe = null;
        Customer customer = null;
        Order order = null;

        expectedTitle = "Somerville Swag";
        expectedContentPane = new LandingPage(oldframe, customer, order).root;
        expectedDefaultCloseOperation = JFrame.EXIT_ON_CLOSE;
        expectedVisibility = false;
        expectedRelativeLocation = null;

        expectedJFrame = new JFrame();
        expectedJFrame.setTitle(expectedTitle);
        expectedJFrame.setContentPane(expectedContentPane);
        expectedJFrame.setDefaultCloseOperation(expectedDefaultCloseOperation);
        expectedJFrame.pack();
        expectedJFrame.setLocationRelativeTo(expectedRelativeLocation);
        expectedJFrame.setVisible(expectedVisibility);
    }

    @Test
    void shouldBuildJFrameWithAllSetters() {
        JFrame actualJFrame = new JFrameBuilder.Builder()
                .withTitle(expectedTitle)
                .withContentPane(expectedContentPane)
                .withDefaultCloseOperation(expectedDefaultCloseOperation)
                .isVisible(expectedVisibility)
                .build();

        assertEquals(expectedJFrame.getTitle(), actualJFrame.getTitle());
        assertEquals(expectedJFrame.getContentPane(), actualJFrame.getContentPane());
        assertEquals(expectedJFrame.getDefaultCloseOperation(), actualJFrame.getDefaultCloseOperation());
        assertEquals(expectedJFrame.isVisible(), actualJFrame.isVisible());
    }

    @Test
    void shouldBuildDefaultJFrame() {
        JFrame actualJFrame = new JFrameBuilder.Builder()
                .buildDefaultJFrame(expectedContentPane, expectedVisibility);

        assertEquals(expectedJFrame.getTitle(), actualJFrame.getTitle());
        assertEquals(expectedJFrame.getContentPane(), actualJFrame.getContentPane());
        assertEquals(expectedJFrame.getDefaultCloseOperation(), actualJFrame.getDefaultCloseOperation());
        assertEquals(expectedJFrame.isVisible(), actualJFrame.isVisible());

    }
}