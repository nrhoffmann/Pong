package io.github.nrhoffmann.pong.gui;

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
    private int bounces;

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

    void incrementPointCount() {
        ++points;
        SwingUtilities.invokeLater(this::update);
        if (thisPointMakesMeWin()) {
            GamePane.togglePauseGame(); // Impossible to gain a point if it was already paused, so this definitely stops the game - unless two happen in the same tick, and then we've broken something anyway

            Hiscores hiscores = Hiscores.instance();

            if (bounces > hiscores.lowestExistingHiscore()) {
                String initials;
                if (template.charAt(0) == 'C') {
                    initials = "CMP";
                } else {
                    initials = JOptionPane.showInputDialog("What are your initials?");
                }
                hiscores.add(initials, bounces);
            }

            StringBuilder message = new StringBuilder("Scores:\n");

            int place = 0;
            for (int i = hiscores.getElements().length - 1; i >= 0; i--) {
                message.append(String.format("%d: %s %8d%n", ++place, hiscores.getElements()[i].getName(), hiscores.getElements()[i].getScore()));
            }

            JOptionPane.showMessageDialog(null, message);

            System.exit(0);
        }
    }

    void incrementBounceCount() {
        ++bounces;
    }

    boolean thisPointMakesMeWin() {
        return points > 10; // todo and difference greater than 2? how does this work anyway? or multiply times # of balls?
    }

    private void update() {
        display.setText(String.format(template, points));
    }

    public JLabel getDisplay() {
        return display;
    }
}
