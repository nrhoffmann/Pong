package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;

class ScoreBoard extends JPanel {

    final Score COMPUTER = new Score("COMPUTER");
    final Score HUMAN = new Score("HUMAN");

    ScoreBoard() {
        super(new GridLayout(1, 2));
        setBackground(Color.ORANGE);

        add(COMPUTER.getDisplay());
        add(HUMAN.getDisplay());
    }
}
