package org.somerville.swag.display;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class JFrameBuilderTest {

    @Test
    void shouldBuildJFrameWithAllSetters() {
        String expectedTitle = "Somerville Swag";
        Container expectedContentPane = spy(JPanel.class);
        int expectedDefaultCloseOperation = JFrame.EXIT_ON_CLOSE;
        boolean expectedVisibility = false;
        Component expectedRelativeLocation = null;

        JFrame expectedJFrame = new JFrame();
        expectedJFrame.setTitle(expectedTitle);
        expectedJFrame.setContentPane(expectedContentPane);
        expectedJFrame.setDefaultCloseOperation(expectedDefaultCloseOperation);
        expectedJFrame.pack();
        expectedJFrame.setLocationRelativeTo(expectedRelativeLocation);
        expectedJFrame.setVisible(expectedVisibility);

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
        String expectedTitle = "Somerville Swag";
        Container expectedContentPane = spy(JPanel.class);
        int expectedDefaultCloseOperation = JFrame.EXIT_ON_CLOSE;
        boolean expectedVisibility = false;
        Component expectedRelativeLocation = null;

        JFrame expectedJFrame = new JFrame();
        expectedJFrame.setTitle(expectedTitle);
        expectedJFrame.setContentPane(expectedContentPane);
        expectedJFrame.setDefaultCloseOperation(expectedDefaultCloseOperation);
        expectedJFrame.pack();
        expectedJFrame.setLocationRelativeTo(expectedRelativeLocation);
        expectedJFrame.setVisible(expectedVisibility);

        JFrame actualJFrame = new JFrameBuilder.Builder()
                .buildDefaultJFrame(expectedContentPane, expectedVisibility);

        assertEquals(expectedJFrame.getTitle(), actualJFrame.getTitle());
        assertEquals(expectedJFrame.getContentPane(), actualJFrame.getContentPane());
        assertEquals(expectedJFrame.getDefaultCloseOperation(), actualJFrame.getDefaultCloseOperation());
        assertEquals(expectedJFrame.isVisible(), actualJFrame.isVisible());

    }
}