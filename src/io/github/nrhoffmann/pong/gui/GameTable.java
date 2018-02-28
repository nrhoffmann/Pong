package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.physics.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameTable extends JPanel{
    private Ball ball;
    private final Dimension SIZE;

    GameTable(Dimension dimension) {
        SIZE = dimension;

        setPreferredSize(SIZE);
        setBackground(Color.DARK_GRAY);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getPoint());
            }
        });

        ball = new Ball();

        new Timer(5, event -> {
            ball.step();
            repaint();
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(ball.FOREGROUND);
        g2.fill(ball.getBoundingBox());
//        g2.fillOval(ball.center.x, ball.center.y, ball.DIAMETER, ball.DIAMETER);
        Toolkit.getDefaultToolkit().sync(); // todo what does this do?
    }

    public Ball getBall() {
        return ball;
    }

    class Ball {
        private final int RADIUS = 16;
        private final Color FOREGROUND = Color.WHITE; //new Color(128, 128, 128);
        private final int MAX_Y = GameTable.this.SIZE.height - RADIUS; //todo deal with scoreboard making playing field smaller
        private final int MIN_Y = RADIUS;

        private Vector vector;
        private Point center;

        Ball() {
            center = new Point(GameTable.this.SIZE.width / 2 - RADIUS, GameTable.this.SIZE.height / 2 - RADIUS);
            vector = new Vector();
        }

        void step() {
            int deltaX = (int) vector.getSpeedX();
            int deltaY = (int) vector.getSpeedY();
            if (center.y + deltaY < MIN_Y || center.y + deltaY > MAX_Y) {
                deltaY = Math.max(deltaY, MIN_Y - center.y); //todo this might be ugly, or it might simulate mass compression
                deltaY = Math.min(deltaY, MAX_Y - center.y);
                vector.bounceY();
            }
            center.translate(deltaX, deltaY);
        }

        public Rectangle getBoundingBox() {
            Rectangle boundingBox = new Rectangle(center);
            boundingBox.grow(RADIUS, RADIUS);

            return boundingBox;
        }
    }

}
