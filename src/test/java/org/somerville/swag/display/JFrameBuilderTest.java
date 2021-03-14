package org.somerville.swag.display;

import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entity.Customer;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class JFrameBuilderTest {

    @Test
    void shouldBuildJFrameWithAllSetters() {
        JFrame oldframe = new JFrame();
        Customer customer = new Customer();
        Container expectedContentPane = new LandingPage(oldframe, customer).root;

        String expectedTitle = "Somerville Swag";
        Container spyContentPane = spy(expectedContentPane.getClass());
        int expectedDefaultCloseOperation = JFrame.EXIT_ON_CLOSE;
        boolean expectedVisibility = false;
        Component expectedRelativeLocation = null;

        JFrame expectedJFrame = new JFrame();
        expectedJFrame.setTitle(expectedTitle);
        expectedJFrame.setContentPane(spyContentPane);
        expectedJFrame.setDefaultCloseOperation(expectedDefaultCloseOperation);
        expectedJFrame.pack();
        expectedJFrame.setLocationRelativeTo(expectedRelativeLocation);
        expectedJFrame.setVisible(expectedVisibility);

        JFrame actualJFrame = new JFrameBuilder.Builder()
                .withTitle(expectedTitle)
                .withContentPane(spyContentPane)
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
        JFrame oldframe = new JFrame();
        Customer customer = new Customer();
        Container expectedContentPane = new LandingPage(oldframe, customer).root;

        String expectedTitle = "Somerville Swag";
        Container spyContentPane = spy(expectedContentPane.getClass());
        int expectedDefaultCloseOperation = JFrame.EXIT_ON_CLOSE;
        boolean expectedVisibility = false;
        Component expectedRelativeLocation = null;

        JFrame expectedJFrame = new JFrame();
        expectedJFrame.setTitle(expectedTitle);
        expectedJFrame.setContentPane(spyContentPane);
        expectedJFrame.setDefaultCloseOperation(expectedDefaultCloseOperation);
        expectedJFrame.pack();
        expectedJFrame.setLocationRelativeTo(expectedRelativeLocation);
        expectedJFrame.setVisible(expectedVisibility);

        JFrame actualJFrame = new JFrameBuilder.Builder()
                .buildDefaultJFrame(spyContentPane, expectedVisibility);

        assertEquals(expectedJFrame.getTitle(), actualJFrame.getTitle());
        assertEquals(expectedJFrame.getContentPane(), actualJFrame.getContentPane());
        assertEquals(expectedJFrame.getDefaultCloseOperation(), actualJFrame.getDefaultCloseOperation());
        assertEquals(expectedJFrame.isVisible(), actualJFrame.isVisible());

    }
}