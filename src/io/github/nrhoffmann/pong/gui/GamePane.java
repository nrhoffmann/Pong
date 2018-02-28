package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;

import static io.github.nrhoffmann.pong.gui.EndZoneChecker.Result.*;

class GamePane extends JPanel {
    private static final Dimension SIZE = new Dimension(1080, 720);

    private ScoreBoard scoreBoard = new ScoreBoard();
    private GameTable gameTable = new GameTable(SIZE);
    private EndZoneChecker endZoneChecker = new EndZoneChecker(gameTable);

    GamePane() {
        super(new BorderLayout());

        add(scoreBoard, BorderLayout.NORTH);
        add(gameTable, BorderLayout.CENTER);

        new Timer(100, (event) -> {
            EndZoneChecker.Result result = endZoneChecker.check(gameTable.getBall());

            if (result == LEFT)
                scoreBoard.HUMAN.increment();
            else if (result == RIGHT)
                scoreBoard.COMPUTER.increment();
        }).start();
    }
}
