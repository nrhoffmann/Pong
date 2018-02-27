package gui;

import javax.swing.*;
import java.awt.*;

class ScoreBoard extends JPanel {

    final Score AI = new Score("AI");
    final Score HUMAN = new Score("Human");

    ScoreBoard() {
        super(new GridLayout(1, 2));
        setBackground(Color.ORANGE);

        add(AI.getDisplay());
        add(HUMAN.getDisplay());
    }
}
