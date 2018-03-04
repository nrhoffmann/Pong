package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

class GameTable extends JPanel {
    private final List<Ball> BALLS = new LinkedList<>();
    private final List<Paddle> LEFT_PADDLES = new LinkedList<>();
    private final List<Paddle> RIGHT_PADDLES = new LinkedList<>();

    GameTable() {
        setBackground(Color.DARK_GRAY);

        new Timer(5, event -> {
            tick();
            apply();
            prepareNextTick();
        }).start();
    }

    private void tick() {
        LEFT_PADDLES.forEach(Paddle::tick);
        RIGHT_PADDLES.forEach(Paddle::tick);
        BALLS.forEach(Ball::tick);
    }

    private void apply() {
        Graphics2D graphics = (Graphics2D) getGraphics();

        paintBalls(graphics);
        paintPaddles(graphics);
    }

    private void paintBalls(Graphics2D graphics) {
        for (Ball ball : BALLS) {
            ball.paint(graphics);
        }
    }

    private void paintPaddles(Graphics2D graphics) {
        for (Paddle paddle : LEFT_PADDLES) {
            paddle.paint(graphics);
        }

        for (Paddle paddle : RIGHT_PADDLES) {
            paddle.paint(graphics);
        }
    }

    private void prepareNextTick() {
        for (Ball ball : BALLS) {
            if (ball.getVector().getSpeedX() < 0) {
                ball.setPotentiallyCollidablePaddles(LEFT_PADDLES);
            } else {
                ball.setPotentiallyCollidablePaddles(RIGHT_PADDLES);
            }
        }
    }

    void addBall(Ball ball) {
        BALLS.add(ball);
    }

    void addPaddle(Paddle paddle) {
        if (paddle.SIDE == Side.LEFT)
            LEFT_PADDLES.add(paddle);
        else if (paddle.SIDE == Side.RIGHT)
            RIGHT_PADDLES.add(paddle);
    }
}
