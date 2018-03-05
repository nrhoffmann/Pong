package io.github.nrhoffmann.pong.control;

import io.github.nrhoffmann.pong.gui.Ball;
import io.github.nrhoffmann.pong.gui.GamePane;
import io.github.nrhoffmann.pong.gui.GameTable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Computer implements Controller {
    private final List<Ball> BALLS;
    private Ball trackingBall;

    private ThreadLocalRandom rand = ThreadLocalRandom.current();

    private int approximateY = GamePane.GAME_TABLE_HEIGHT / 2;

    public Computer(GameTable gameTable) {
        BALLS = gameTable.getBALLS();
    }

    @Override
    public int nextInput() {
        if (trackingBall == null)
            trackingBall = BALLS.get(0);


        if (trackingBall.getVector().getSpeedX() > 0)
            trackingBall = BALLS.get(rand.nextInt(BALLS.size()));


        int input = 0;

        if (trackingBall.getDrawLocation().getCenterY() > approximateY)
                input = 3;
        else if (trackingBall.getDrawLocation().getCenterY() < approximateY)
                input = -3;

        approximateY += input;

        System.out.println(input);

        return input;
    }
}
