package gui;

import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel {

    private ScoreBoard scoreBoard = new ScoreBoard();

    GamePanel() {
        super(new BorderLayout());

        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(1080, 720));

        add(scoreBoard, BorderLayout.NORTH);
    }
}
