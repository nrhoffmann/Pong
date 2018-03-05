package io.github.nrhoffmann.pong.control;

import io.github.nrhoffmann.pong.gui.Ball;
import io.github.nrhoffmann.pong.gui.GameTable;

import java.util.List;
import java.util.Random;

public class Computer implements Controller {
    private final List<Ball> BALLS;

    private Random rand = new Random();

    private int ystuff = 400;

    public Computer(GameTable gameTable) {
        BALLS = gameTable.getBALLS();
    }

    @Override
    public int nextInput() {
        int minx = 600; //todo only works on left
        int y = 0;

        for (Ball ball : BALLS) {
            if (ball.getDrawLocation().x < minx) {
                minx = ball.getDrawLocation().x;
                y = ball.getDrawLocation().y;
            }
        }

        int delta = rand.nextInt(2) * (y > ystuff ? -2 : 2);
        if (ystuff > 600)
            ystuff = 600;

        if (ystuff < 0)
            ystuff = 0;

        ystuff += delta;
        return y < 360 ? -3 : 3;
    }
}
