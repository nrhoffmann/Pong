package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.control.Computer;
import io.github.nrhoffmann.pong.control.Human;

import javax.swing.*;
import java.awt.*;

import static io.github.nrhoffmann.pong.gui.EndZoneChecker.Result.*;

class GamePane extends JPanel {
    static final int WIDTH = 1080;
    static final int GAME_TABLE_HEIGHT = 720;

    private static final int SCORE_BOARD_HEIGHT = 32;

    private ScoreBoard scoreBoard = new ScoreBoard();
    private GameTable gameTable = new GameTable();

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

        gameTable.addBall(new Ball());

        Paddle computerLeftPaddle = new Paddle(Side.LEFT, new Computer());
        gameTable.addPaddle(computerLeftPaddle);

        Paddle humanRightPaddle = new Paddle(Side.RIGHT, new Human());
        gameTable.addPaddle(humanRightPaddle);
    }

}
