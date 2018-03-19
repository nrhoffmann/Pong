package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GameTable extends JPanel {
    private final List<Ball> BALLS = new LinkedList<>();
    private final List<Paddle> LEFT_PADDLES = new LinkedList<>();
    private final List<Paddle> RIGHT_PADDLES = new LinkedList<>();
    private final List<Rectangle> CHANGED_MASKS = new LinkedList<>();

    GameTable() {
        setBackground(Color.DARK_GRAY);

        new Timer(5, event -> {
            if (GamePane.isGameActive()) { // Only tick if game is active (didn't win/not paused)
                tick();
                apply();
                prepareNextTick();
            }
        }).start();
    }

    private void tick() {
        LEFT_PADDLES.forEach(Paddle::tick);
        RIGHT_PADDLES.forEach(Paddle::tick);
        BALLS.forEach(Ball::tick);
    }

    private void apply() {
        for (Paddle paddle : LEFT_PADDLES) {
            CHANGED_MASKS.add(paddle.changedMask());
        }

        for (Paddle paddle : RIGHT_PADDLES) {
            CHANGED_MASKS.add(paddle.changedMask());
        }

        for (Ball ball : BALLS) {
            CHANGED_MASKS.add(ball.changedMask());
        }

        for (Rectangle changedMask : CHANGED_MASKS) {
            repaint(changedMask);
        }
    }

    private void prepareNextTick() {
        CHANGED_MASKS.clear();

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

    public List<Ball> getBALLS() {
        return BALLS;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        for (Paddle paddle : LEFT_PADDLES) {
            paddle.paint(graphics);
        }

        for (Paddle paddle : RIGHT_PADDLES) {
            paddle.paint(graphics);
        }

        for (Ball ball : BALLS) {
            ball.paint(graphics);
        }
    }
}
