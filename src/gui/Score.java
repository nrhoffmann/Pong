package gui;

import javax.swing.*;
import java.awt.*;

public class Score {
    private static final Font DISPLAY_FONT;

    static {
        DISPLAY_FONT = new Font(Font.MONOSPACED, Font.BOLD, 22);
    }

    private JLabel display;
    private final String template;
    private int points;

    Score(String label) {
        initDisplay();
        template = label + ": %d";
        SwingUtilities.invokeLater(this::update);
    }

    private void initDisplay() {
        display = new JLabel();
        display.setFont(DISPLAY_FONT);
        display.setHorizontalAlignment(JLabel.CENTER);
    }

    void increment() {
        ++points;
        SwingUtilities.invokeLater(this::update);
    }

    private void update() {
        display.setText(String.format(template, points));
    }

    public JLabel getDisplay() {
        return display;
    }
}
