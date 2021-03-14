package org.somerville.swag.display;

import javax.swing.*;
import java.awt.*;

public class JFrameBuilder {

    public static class Builder {

        private String title;
        private Container contentPane;
        private int defaultCloseOperation;
        private boolean isVisible;
        private JPanel windowAncestor;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withContentPane(Container contentPane) {
            this.contentPane = contentPane;
            return this;
        }

        public Builder withDefaultCloseOperation(int operation) {
            this.defaultCloseOperation = operation;
            return this;
        }

        public Builder isVisible(boolean isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public Builder disposeOfWindowAncestor(JPanel jPanel) {
            this.windowAncestor = jPanel;
            return this;
        }

        public JFrame build() {
            JFrame jFrame = new JFrame();
            jFrame.setTitle(this.title);
            jFrame.setContentPane(this.contentPane);
            jFrame.setDefaultCloseOperation(this.defaultCloseOperation);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(this.isVisible);
            SwingUtilities.getWindowAncestor(this.windowAncestor).dispose();
            return jFrame;
        }

        public void buildDefaultJFrame(Container contentPane) {
            JFrame jFrame = new JFrame();
            jFrame.setTitle("Somerville Swag");
            jFrame.setContentPane(contentPane);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        }

        public void buildDefaultJFrame(Container contentPane, JPanel windowAncestor) {
            JFrame jFrame = new JFrame();
            jFrame.setTitle("Somerville Swag");
            jFrame.setContentPane(contentPane);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
            SwingUtilities.getWindowAncestor(windowAncestor).dispose();
        }
    }
}
