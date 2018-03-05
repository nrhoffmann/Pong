package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.control.Computer;
import io.github.nrhoffmann.pong.control.Human;
import io.github.nrhoffmann.pong.control.MouseWheelBridge;

import javax.swing.*;
import java.awt.*;

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

        Human human = new Human();
        gameTable.addPaddle(new Paddle(Side.RIGHT, human));

        MouseWheelBridge bridge = new MouseWheelBridge();
        addMouseWheelListener(bridge.listener());

        bridge.addConsumer(human);
        bridge.addConsumer(System.out::println); // todo remove before submission
    }

}
