package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;

import static io.github.nrhoffmann.pong.gui.EndZoneChecker.Result.*;

class GamePane extends JPanel {
    private static final int WIDTH = 1080;
    private static final int SCORE_BOARD_HEIGHT = 32;
    private static final int GAME_TABLE_HEIGHT = 720;

    static final int WIDTH = 1080; // https://softwareengineering.stackexchange.com/a/261868/144103
    static final int GAME_TABLE_HEIGHT = 720;

    private static final int SCORE_BOARD_HEIGHT = 32;

    private ScoreBoard scoreBoard = new ScoreBoard();
    private GameTable gameTable = new GameTable();
    private EndZoneChecker endZoneChecker = new EndZoneChecker(gameTable);

    GamePane() {
        GridBagLayout layout = new GridBagLayout();
        layout.rowHeights = new int[]{SCORE_BOARD_HEIGHT, GAME_TABLE_HEIGHT};
        layout.columnWidths = new int[]{WIDTH};
        setLayout(layout);

        GridBagConstraints gameTableConstraints = new GridBagConstraints();
        gameTableConstraints.fill = GridBagConstraints.BOTH;
        gameTableConstraints.gridx = 0;
        gameTableConstraints.gridy = 1;
        add(gameTable, gameTableConstraints);

        GridBagConstraints scoreBoardConstraints = new GridBagConstraints();
        scoreBoardConstraints.fill = GridBagConstraints.BOTH;
        scoreBoardConstraints.gridx = 0;
        scoreBoardConstraints.gridy = 0;
        add(scoreBoard, scoreBoardConstraints);

        new Timer(100, (event) -> {
            EndZoneChecker.Result result = endZoneChecker.check(gameTable.getBall());

            if (result == LEFT)
                scoreBoard.HUMAN.increment();
            else if (result == RIGHT)
                scoreBoard.COMPUTER.increment();
        }).start();
    }

}
