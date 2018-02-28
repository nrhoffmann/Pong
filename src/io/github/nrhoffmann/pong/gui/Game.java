package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;

import static io.github.nrhoffmann.pong.gui.EndZoneChecker.Result.*;

class Game extends JPanel {
    private static final Dimension SIZE = new Dimension(1080, 720);

    private ScoreBoard scoreBoard = new ScoreBoard();
    private PlayingField playingField = new PlayingField(SIZE);
    private EndZoneChecker endZoneChecker = new EndZoneChecker(playingField);

    Game() {
        super(new BorderLayout());

        add(scoreBoard, BorderLayout.NORTH);
        add(playingField, BorderLayout.CENTER);

        new Timer(100, (event) -> {
            EndZoneChecker.Result result = endZoneChecker.check(playingField.getBall());

            if (result == LEFT)
                scoreBoard.HUMAN.increment();
            else if (result == RIGHT)
                scoreBoard.AI.increment();
        }).start();
    }
}
