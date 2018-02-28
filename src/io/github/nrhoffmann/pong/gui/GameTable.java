package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.physics.Vector;

import javax.swing.*;
import java.awt.*;

class GameTable extends JPanel{
    private Ball ball;
    private final Dimension SIZE;

    GameTable(Dimension dimension) {
        SIZE = dimension;
        setBackground(Color.DARK_GRAY);

        ball = new Ball();

        new Timer(5, event -> {
            Rectangle old = new Rectangle(ball.boundingBox);
            ball.step();
            Rectangle changedMask = old.union(ball.boundingBox);
            changedMask.grow(5, 5);
            paintImmediately(changedMask);
        }).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.WHITE);
        graphics.fillOval(ball.boundingBox.x, ball.boundingBox.y, ball.RADIUS * 2, ball.RADIUS * 2);
        Toolkit.getDefaultToolkit().sync();
    }

    public Ball getBall() {
        return ball;
    }

    class Ball {
        private final int RADIUS = 16;
        private final int MAX_Y = GameTable.this.SIZE.height - RADIUS;
        private final int MIN_Y = RADIUS;

        private Vector vector;
        private Rectangle boundingBox;

        Ball() {
            Point centerOfTable = new Point(GameTable.this.SIZE.width / 2 - RADIUS, GameTable.this.SIZE.height / 2 - RADIUS);
            boundingBox = new Rectangle(centerOfTable);
            boundingBox.grow(RADIUS, RADIUS);

            vector = new Vector();
        }

        void step() {
            int deltaX = (int) vector.getSpeedX();
            int deltaY = (int) vector.getSpeedY();
            int centerY = (int) boundingBox.getCenterY();

            if (centerY + deltaY < MIN_Y || centerY + deltaY > MAX_Y) {
                deltaY = Math.max(deltaY, MIN_Y - centerY); //todo this might be ugly, or it might simulate mass compression
                deltaY = Math.min(deltaY, MAX_Y - centerY);
                vector.bounceY();
            }
            boundingBox.translate(deltaX, deltaY);
        }

        Rectangle getBoundingBox() {
            return boundingBox;
        }
    }

}
