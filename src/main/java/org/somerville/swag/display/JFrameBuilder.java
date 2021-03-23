package org.somerville.swag.display;

import javax.swing.*;
import java.awt.*;

public class JFrameBuilder {

    public static class Builder {

        private String title;
        private Container contentPane;
        private int defaultCloseOperation;
        private boolean isVisible;

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

        public JFrame build() {
            JFrame jFrame = new JFrame();
            jFrame.setTitle(this.title);
            jFrame.setContentPane(this.contentPane);
            jFrame.setDefaultCloseOperation(this.defaultCloseOperation);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(this.isVisible);
            return jFrame;
        }

        public JFrame buildDefaultJFrame(Container contentPane, boolean isVisible) {
            JFrame jFrame = new JFrame();
            jFrame.setTitle("Somerville Swag");
            jFrame.setContentPane(contentPane);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.getContentPane().setBackground(new Color(9, 45, 71));
            jFrame.pack();
            jFrame.setMinimumSize(new Dimension(750, 650));
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(isVisible);
            return jFrame;
        }
    }
}
